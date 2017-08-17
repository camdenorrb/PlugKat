@file:Suppress("UNCHECKED_CAST")

package me.camdenorrb.plugkat.ext

import java.lang.reflect.Field


inline fun Class<out Any>.getPrivateField(name: String, block: (Field) -> Unit) {
	getDeclaredField(name).apply { isAccessible = true; block(this); isAccessible = false }
}

inline fun <R : Any> Class<out Any>.getPrivateVar(name: String, reference: Any, block: (R) -> Unit) = getPrivateField(name) {
	block(it.get(reference) as R)
}