package com.arbonik.answerka

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.arbonik.answerka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.restart_menu) {
            AlertDialog.Builder(this)
                .setTitle("Вы уверены, что хотите перезапустить игру?")
                .setPositiveButton("Нет") { _, _ ->
                }
                .setNegativeButton("Да") { _, _ ->
                    (application as AnswerkaApplication).restart()
                    Repository.restart()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }.show()
        }
        return super.onOptionsItemSelected(item)
    }
}