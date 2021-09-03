package com.arbonik.answerka

import android.app.Application
import java.nio.charset.Charset


class AnswerkaApplication : Application() {

    private val charset = Charset.forName("windows-1251")

    override fun onCreate() {
        super.onCreate()
        loadStatement()
        loadTasks()
    }

    private fun loadTasks() {
        loadSoloTasks()
        loadTargetTasks()
        loadUniversalTasks()
        loadGroupTasks()
    }

    private fun loadGroupTasks() {
        var file = resources.assets.open("groups_tasks.txt")
        var reader = file.reader()
        var lines = reader.readLines()
        Repository.groupTasks = lines.toMutableList()
        file.close()
    }

    private fun loadUniversalTasks() {
        var file = resources.assets.open("univers_tasks.txt")
        var reader = file.reader()
        var lines = reader.readLines()
        Repository.universalTasks = lines.toMutableList()
        file.close()
    }

    private fun loadTargetTasks() {
        var file = resources.assets.open("target_tasks.txt")
        var reader = file.reader()
        var lines = reader.readLines()
        Repository.targetTasks = lines.toMutableList()
        file.close()
    }

    private fun loadSoloTasks() {
        var file = resources.assets.open("solo_tasks.txt")
        var reader = file.reader()
        var lines = reader.readLines()
        Repository.soloTasks = lines.toMutableList()
        file.close()
    }

    private fun loadStatement() {
        var file = resources.assets.open("statements.txt")
        var reader = file.reader(charset)
        var lines = reader.readLines()
        Repository.statements = lines.toMutableList()
        file.close()
    }

    fun restart() {
        loadStatement()
        loadTasks()
    }
}