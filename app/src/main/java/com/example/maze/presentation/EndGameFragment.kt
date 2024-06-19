package com.example.maze.presentation

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class EndGameFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Koniec labiryntu")
            .setPositiveButton("Zacznij od nowa") { _, _ ->
                (requireActivity() as MazeActivity).restartGame()
            }
            .setNegativeButton("Wyjść") { _, _ ->
                requireActivity().finish()
            }
        return builder.create()
    }
}
