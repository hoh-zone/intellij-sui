package org.sui.cli.tests

import org.sui.cli.MoveProject
import org.sui.lang.core.types.Address
import org.sui.lang.core.types.Address.Named

interface NamedAddressService {
    fun getNamedAddress(moveProject: MoveProject, name: String): Address.Named?
}

class NamedAddressServiceImpl : NamedAddressService {
    override fun getNamedAddress(moveProject: MoveProject, name: String): Address.Named? = null
}


class NamedAddressServiceTestImpl : NamedAddressService {
    val namedAddresses: MutableMap<String, String> = mutableMapOf()

    override fun getNamedAddress(moveProject: MoveProject, name: String): Named? {
        val value = namedAddresses[name] ?: return null
        return Named(name, value, moveProject)
    }
}