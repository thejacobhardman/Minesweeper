package com.example.minesweeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var cells: Array<Array<Cell>>
    lateinit var adapter: GridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cells = Array(10) {row ->
            Array(10) {col ->
                Cell(false, row, col)
            }
        }

        adapter = GridAdapter(this, cells.flatten())
        GameBoard.adapter = adapter
    }
}