package com.arbonik.answerka

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import java.nio.charset.Charset


class AnswerkaApplication : Application() {

    private val charset = Charsets.UTF_8//Charset.forName("windows-1251")

    override fun onCreate() {
        super.onCreate()
        activateAppMetrica()
        MobileAds.initialize(this){}
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
        var reader = file.reader(charset)
        var lines = reader.readLines()
        Repository.groupTasks = lines.toMutableList()
        file.close()
    }

    private fun loadUniversalTasks() {
        var file = resources.assets.open("univers_tasks.txt")
        var reader = file.reader(charset)
        var lines = reader.readLines()
        Repository.universalTasks = lines.toMutableList()
        file.close()
    }

    private fun loadTargetTasks() {
        var file = resources.assets.open("target_tasks.txt")
        var reader = file.reader(charset)
        var lines = reader.readLines()
        Repository.targetTasks = lines.toMutableList()
        file.close()
    }

    private fun loadSoloTasks() {
        var file = resources.assets.open("solo_tasks.txt")
        var reader = file.reader(charset)
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

    private fun activateAppMetrica() {
        val appMetricaConfig: YandexMetricaConfig =
            YandexMetricaConfig.newConfigBuilder("a5e1f70e-0c4a-49a4-86eb-65c6a42ec651")
                .handleFirstActivationAsUpdate(isFirstActivationAsUpdate())
                .withLocationTracking(true)
                .withStatisticsSending(true)
                .build()
        YandexMetrica.activate(applicationContext, appMetricaConfig)
    }

    private fun isFirstActivationAsUpdate(): Boolean {
        val pref = getSharedPreferences("first",MODE_PRIVATE)
        val result = pref.getBoolean("isFirst", true)
        pref
            .edit()
            .putBoolean("isFirst", false)
            .apply()
        return result
    }
}