package moe.feng.kotlinyan.common

fun <E> Collection<E>?.isEmptyOrNull(): Boolean {
	return this?.isEmpty() ?: true
}

fun <E> Collection<E>?.isNotEmpty(): Boolean {
	return !this.isEmptyOrNull()
}