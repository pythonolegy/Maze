package com.example.maze.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.maze.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            startMazeActivity()
        }
    }

    private fun startMazeActivity() {
        val intent = Intent(this, MazeActivity::class.java)
        startActivity(intent)
    }
}
