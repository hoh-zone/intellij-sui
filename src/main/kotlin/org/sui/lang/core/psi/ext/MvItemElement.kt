package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.MvNameIdentifierOwner

// 定义一个接口MvItemElement，该接口继承自MvVisibilityOwner、MvDocAndAttributeOwner和MvNameIdentifierOwner
interface MvItemElement: MvVisibilityOwner, MvDocAndAttributeOwner, MvNameIdentifierOwner