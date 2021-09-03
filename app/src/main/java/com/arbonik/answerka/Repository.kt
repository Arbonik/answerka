package com.arbonik.answerka

import com.arbonik.answerka.data.OurTask

object Repository {
    private val NO_MORE_DATA = "Данные кончились"

    var names = mutableListOf<String>()
    var checkedNames = mutableListOf<String>()

    var statements = mutableListOf<String>()
    var soloTasks = mutableListOf<String>()
    var groupTasks = mutableListOf<String>()
    var universalTasks = mutableListOf<String>()
    var targetTasks = mutableListOf<String>()

    fun getSoloTasks(): String {
        var lists = listOf(soloTasks, universalTasks)
            .filter {
                it.isNotEmpty()
            }
        if (lists.isNotEmpty()) {
            var list = lists.random()
            var item = list.random()
            list.remove(item)
            return item
        } else return NO_MORE_DATA
    }

    fun getGroupTasks(): String {
        var lists = listOf(groupTasks, universalTasks)
            .filter {
                it.isNotEmpty()
            }
        if (lists.isNotEmpty()) {
            var list = lists.random()
            var item = list.random()
            list.remove(item)
            return item
        } else return NO_MORE_DATA
    }

    fun getTask(): OurTask {
        if (checkedNames.isEmpty())
            return OurTask(null)
        return when (checkedNames.size) {
            1 -> {
                val task = getSoloTasks()
                OurTask(task)
            }
            else -> {
                val task = getGroupTasks()
                OurTask(task)
            }
        }
    }

    fun restart() {
        names.clear()
        checkedNames.clear()
    }
}