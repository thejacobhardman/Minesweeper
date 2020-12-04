package com.example.minesweeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.grid_item.view.*
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    lateinit var cells: Array<Array<Cell>>
    lateinit var adapter: GridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createGrid()
        placeBombs()

        GameBoard.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                revealCell(cells.flatten().get(position))
                v.cell.setImageResource(cells.flatten().get(position).returnImage())
            }

        RestartButton.setOnClickListener {
            createGrid()
            placeBombs()
            GameText.text = "Play!"
        }
    }

    fun createGrid() {
        cells = Array(10) {row ->
            Array(10) {col ->
                Cell(true, row, col) //change this back to false before finishing
            }
        }
        adapter = GridAdapter(this, cells.flatten())
        GameBoard.adapter = adapter
    }

    fun revealCell(cell: Cell) {
        cell.faceUp = true
    }

    fun placeBombs() {
        for (i in 1..5) {
            val row = Random.nextInt(0..9)
            val col = Random.nextInt(0..9)
            cells[row][col].isBomb = true
        }
    }
}