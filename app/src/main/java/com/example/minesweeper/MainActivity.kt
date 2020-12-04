package com.example.minesweeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.grid_item.view.*

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

        GameBoard.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                revealCell(cells.flatten().get(position))
                v.cell.setImageResource(cells.flatten().get(position).returnImage())
            }
    }

    fun revealCell(cell: Cell) {
        cell.faceUp = true
    }
}