package com.example.maze.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.maze.R

class MazeActivity : AppCompatActivity() {
    private val maze = arrayOf(
        intArrayOf(12, 10, 9, 0),
        intArrayOf(6, 5, 6, 13),
        intArrayOf(10, 8, 9, 12),
        intArrayOf(1, 5, 6, 28)
    )
    private var currentRow = 0
    private var currentCol = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maze)

        showRoom()
    }

    private fun showRoom() {
        if (maze[currentRow][currentCol] == 0) {
            // Jeśli dotarliśmy do komnaty wyjściowej (0), pokazujemy fragment zakończenia gry
            EndGameFragment().show(supportFragmentManager, "EndGamePopUp")
        } else {
            // W przeciwnym razie pokazujemy fragment komnaty na podstawie aktualnej pozycji
            val roomFragment = RoomFragment.newInstance(currentRow, currentCol, maze)
            supportFragmentManager.beginTransaction()
                .replace(R.id.roomContainer, roomFragment)
                .commit()
        }
    }

    fun moveUp() {
        // Sprawdzamy czy ruch w górę jest możliwy
        if (canMoveUp()) {
            currentRow--
            showRoom()
        }
    }

    fun moveDown() {
        // Sprawdzamy czy ruch w dół jest możliwy
        if (canMoveDown()) {
            currentRow++
            showRoom()
        }
    }

    fun moveLeft() {
        // Sprawdzamy czy ruch w lewo jest możliwy
        if (canMoveLeft()) {
            currentCol--
            showRoom()
        }
    }

    fun moveRight() {
        // Sprawdzamy czy ruch w prawo jest możliwy
        if (canMoveRight()) {
            currentCol++
            showRoom()
        }
    }

    fun restartGame() {
        // Resetujemy pozycję gracza na początkową i pokazujemy pierwszą komnatę
        currentRow = 0
        currentCol = 0
        showRoom()
    }

    // Metody pomocnicze sprawdzające możliwość ruchu w danej komnacie

    private fun canMoveUp(): Boolean {
        // Sprawdzamy czy możemy iść w górę
        val currentRoom = maze[currentRow][currentCol]
        return currentRow > 0 && (currentRoom and 4 != 0)
    }

    private fun canMoveDown(): Boolean {
        // Sprawdzamy czy możemy iść w dół
        val currentRoom = maze[currentRow][currentCol]
        return currentRow < maze.size - 1 && (currentRoom and 8 != 0)
    }

    private fun canMoveLeft(): Boolean {
        // Sprawdzamy czy możemy iść w lewo
        val currentRoom = maze[currentRow][currentCol]
        return currentCol > 0 && (currentRoom and 1 != 0)
    }

    private fun canMoveRight(): Boolean {
        // Sprawdzamy czy możemy iść w prawo
        val currentRoom = maze[currentRow][currentCol]
        return currentCol < maze[0].size - 1 && (currentRoom and 2 != 0)
    }
}
