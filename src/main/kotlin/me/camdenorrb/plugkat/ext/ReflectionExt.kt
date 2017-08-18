@file:Suppress("UNCHECKED_CAST")

package me.camdenorrb.plugkat.ext

import java.lang.reflect.Field


fun Class<out Any>.getPrivateField(name: String): Field {

	val field = getDeclaredField(name)
	if (field.isAccessible.not()) field.isAccessible = true

	return field
}

fun <R : Any> Class<out Any>.getPrivateVar(name: String, reference: Any): R {
	return getPrivateField(name).get(reference) as R
}