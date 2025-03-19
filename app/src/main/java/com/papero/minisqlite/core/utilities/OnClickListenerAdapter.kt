package com.papero.minisqlite.core.utilities

class OnClickListenerAdapter<T>(val clickListener: (item: T) -> Unit) {
    fun onClick(item: T) = clickListener(item)
}