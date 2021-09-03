package com.arbonik.answerka.data

data class OurTask(
    val task : String? = null
){
    val isActive : Boolean
        get() = task != null
}
