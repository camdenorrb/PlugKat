@file:Suppress("UNCHECKED_CAST")

package me.camdenorrb.plugkat.ext

import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


fun <T : Any> KClass<out T>.getPrivateProp(name: String): KProperty1<out T, Any?> {
	val field = memberProperties.find { it.name == name } ?: error("Private field not found \"$name\"")
	if (field.isAccessible.not()) field.isAccessible = true
	return field
}

fun <R : Any> KClass<out Any>.getPrivateVar(name: String, reference: Any) = getPrivateProp(name).call(reference) as R