package com.example.maze.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.maze.R

class RoomFragment : Fragment() {
    private var currentRow: Int = 0
    private var currentCol: Int = 0
    private lateinit var maze: Array<IntArray>

    companion object {
        private const val CURRENT_ROW = "current_row"
        private const val CURRENT_COL = "current_col"
        private const val MAZE = "Maze"

        fun newInstance(currentRow: Int, currentCol: Int, maze: Array<IntArray>): RoomFragment {
            val fragment = RoomFragment()
            val args = Bundle()
            args.putInt(CURRENT_ROW, currentRow)
            args.putInt(CURRENT_COL, currentCol)
            args.putSerializable(MAZE, maze)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_room, container, false)
        currentRow = arguments?.getInt(CURRENT_ROW) ?: 0
        currentCol = arguments?.getInt(CURRENT_COL) ?: 0
        maze = arguments?.getSerializable(MAZE) as? Array<IntArray> ?: emptyArray()

        displayRoom(view)
        setupNavigation(view)
        return view
    }

    private fun setupNavigation(view: View) {
        val mazeActivity = requireActivity() as MazeActivity
        val roomCode = maze[currentRow][currentCol]

        view.findViewById<ImageButton>(R.id.button_up).apply {
            isEnabled = roomCode and 4 != 0
            visibility = if (isEnabled) View.VISIBLE else View.INVISIBLE
            setOnClickListener {
                mazeActivity.moveUp()
            }
        }
        view.findViewById<ImageButton>(R.id.button_down).apply {
            isEnabled = roomCode and 8 != 0
            visibility = if (isEnabled) View.VISIBLE else View.INVISIBLE
            setOnClickListener {
                mazeActivity.moveDown()
            }
        }
        view.findViewById<ImageButton>(R.id.button_left).apply {
            isEnabled = roomCode and 1 != 0
            visibility = if (isEnabled) View.VISIBLE else View.INVISIBLE
            setOnClickListener {
                mazeActivity.moveLeft()
            }
        }
        view.findViewById<ImageButton>(R.id.button_right).apply {
            isEnabled = roomCode and 2 != 0
            visibility = if (isEnabled) View.VISIBLE else View.INVISIBLE
            setOnClickListener {
                mazeActivity.moveRight()
            }
        }
    }

    private fun displayRoom(view: View) {
        val gridView = view.findViewById<GridView>(R.id.maze_grid)
        val mazeArray = mutableListOf<String>()

        for (i in maze.indices) {
            for (j in maze[i].indices) {
                val cellText = if (i == currentRow && j == currentCol) "*" else maze[i][j].toString()
                mazeArray.add(cellText)
            }
        }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, mazeArray)
        gridView.adapter = adapter
    }
}
