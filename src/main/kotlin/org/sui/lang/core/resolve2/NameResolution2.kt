package org.sui.lang.core.resolve2

import org.sui.lang.core.psi.*
import org.sui.lang.core.psi.ext.*
import org.sui.lang.core.resolve.*
import org.sui.lang.core.resolve.ref.*
import org.sui.lang.core.resolve2.ref.ResolutionContext
import org.sui.lang.core.types.Address
import org.sui.lang.core.types.Address.Named
import org.sui.lang.core.types.address
import org.sui.lang.index.MvModuleIndex


fun processStructLitFieldResolveVariants(
    litField: MvStructLitField,
    isCompletion: Boolean,
    processor: MvResolveProcessor,
): Boolean {
    val fieldsOwner = litField.structLitExpr.path.reference?.resolveFollowingAliases() as? MvFieldsOwner
    if (fieldsOwner != null && processFieldDeclarations(fieldsOwner, processor)) return true
    // if it's a shorthand, try to resolve to the underlying binding pat
    if (!isCompletion && litField.expr == null) {
        val ctx = ResolutionContext(litField, false)
        // return is ignored as struct lit field cannot be marked as resolved through binding pat
        processNestedScopesUpwards(litField, NAMES, ctx, processor)
    }
    return false
}

fun processStructPatFieldResolveVariants(
    field: MvPatFieldFull,
    processor: MvResolveProcessor
): Boolean {
    val resolved = field.parentPatStruct.path.reference?.resolveFollowingAliases()
    val resolvedStruct = resolved as? MvFieldsOwner ?: return false
    return processFieldDeclarations(resolvedStruct, processor)
}

fun processPatBindingResolveVariants(
    binding: MvPatBinding,
    isCompletion: Boolean,
    originalProcessor: MvResolveProcessor
): Boolean {
    // field pattern shorthand
    if (binding.parent is MvPatField) {
        val parentPat = binding.parent.parent as MvPatStruct
        val structItem = parentPat.path.reference?.resolveFollowingAliases()
        // can be null if unresolved
        if (structItem is MvFieldsOwner) {
            if (processFieldDeclarations(structItem, originalProcessor)) return true
            if (isCompletion) return false
        }
    }
    // copied as is from the intellij-rust, handles all items that can be matched in match arms
    val processor = originalProcessor.wrapWithFilter { entry ->
        if (originalProcessor.acceptsName(entry.name)) {
            val element = entry.element
            val isConstantLike = element.isConstantLike
            val isPathOrDestructable = when (element) {
                is MvEnum, is MvEnumVariant, is MvStruct -> true
                else -> false
            }
            isConstantLike || (isCompletion && isPathOrDestructable)
        } else {
            false
        }
    }
    val ctx = ResolutionContext(binding, isCompletion)
    return processNestedScopesUpwards(binding, if (isCompletion) TYPES_N_NAMES else NAMES, ctx, processor)
}

fun resolveBindingForFieldShorthand(
    element: MvMandatoryReferenceElement,
): List<MvNamedElement> {
    return collectResolveVariants(element.referenceName) {
        processNestedScopesUpwards(
            element,
            setOf(Namespace.NAME),
            ResolutionContext(element, isCompletion = false),
            it
        )
    }
}

fun processNestedScopesUpwards(
    scopeStart: MvElement,
    ns: Set<Namespace>,
    ctx: ResolutionContext,
    processor: MvResolveProcessor
): Boolean {
    val prevScope = hashMapOf<String, Set<Namespace>>()
    return walkUpThroughScopes(
        scopeStart,
        stopAfter = { it is MvModule }
    ) { cameFrom, scope ->
        // state between shadowing processors passed through prevScope
        processWithShadowingAndUpdateScope(prevScope, ns, processor) { shadowingProcessor ->
            processItemsInScope(
                scope, cameFrom, ns, ctx, shadowingProcessor
            )
        }
    }
}

fun processModulePathResolveVariants(
    ctx: ResolutionContext,
    address: Address,
    processor: MvResolveProcessor,
): Boolean {
    // if no project, cannot use the index
    val moveProject = ctx.moveProject
    if (moveProject == null) return false

    val project = ctx.element.project
    val searchScope = moveProject.searchScope()

    val addressMatcher = processor.wrapWithFilter { e ->
        val candidate = e.element as? MvModule ?: return@wrapWithFilter false
        val candidateAddress = candidate.address(moveProject)
        val sameValues = Address.equals(address, candidateAddress)

        if (ctx.isCompletion && sameValues) {
            // compare named addresses by name in case of the same values for the completion
            if (address is Named && candidateAddress is Named && address.name != candidateAddress.name)
                return@wrapWithFilter false
        }

        sameValues
    }

    val targetNames = addressMatcher.names
    // completion
    if (targetNames == null) {
        val moduleNames = MvModuleIndex.getAllModuleNames(project)
        moduleNames.forEach { moduleName ->
            val modules = MvModuleIndex.getModulesByName(project, moduleName, searchScope)
            for (module in modules) {
                if (addressMatcher.process(moduleName, MODULES, module)) return true
            }
        }
        return false
    }

//    var stop = false
    for (targetModuleName in targetNames) {
        val modules = MvModuleIndex.getModulesByName(project, targetModuleName, searchScope)
        for (module in modules) {
            if (addressMatcher.process(targetModuleName, module, MODULES)) return true
        }
    }

    return false
}

inline fun processWithShadowingAndUpdateScope(
    prevScope: MutableMap<String, Set<Namespace>>,
    ns: Set<Namespace>,
    processor: MvResolveProcessor,
    f: (MvResolveProcessor) -> Boolean
): Boolean {
    val currScope = mutableMapOf<String, Set<Namespace>>()
    val shadowingProcessor = processor.wrapWithShadowingProcessorAndUpdateScope(prevScope, currScope, ns)
    return try {
        f(shadowingProcessor)
    } finally {
        prevScope.putAll(currScope)
    }
}

//inline fun processWithShadowing(
//    prevScope: Map<String, Set<Namespace>>,
//    ns: Set<Namespace>,
//    processor: MvResolveProcessor,
//    f: (MvResolveProcessor) -> Boolean
//): Boolean {
//    val shadowingProcessor = processor.wrapWithShadowingProcessor(prevScope, ns)
//    return f(shadowingProcessor)
//}


fun walkUpThroughScopes(
    start: MvElement,
    stopAfter: (MvElement) -> Boolean,
    handleScope: (cameFrom: MvElement, scope: MvElement) -> Boolean,
): Boolean {
    var cameFrom = start
    var scope = start.context as MvElement?
    while (scope != null) {
        if (handleScope(cameFrom, scope)) return true

        if (stopAfter(scope)) break

        cameFrom = scope
        scope = scope.context as? MvElement
    }

    return false
}

private fun processFieldDeclarations(struct: MvFieldsOwner, processor: MvResolveProcessor): Boolean =
    struct.fields.any { field ->
        val name = field.name
        processor.process(name, NAMES, field)
    }
