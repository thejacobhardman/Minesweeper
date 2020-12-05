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
        placeNumbers()

        GameBoard.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                revealCell(cells.flatten().get(position))
                v.cell.setImageResource(cells.flatten().get(position).returnImage())
            }

        RestartButton.setOnClickListener {
            createGrid()
            placeBombs()
            placeNumbers()
            GameText.text = "Play!"
        }
    }

    private fun createGrid() {
        cells = Array(10) {row ->
            Array(10) {col ->
                Cell(true, row, col) //change this back to false before finishing
            }
        }
        adapter = GridAdapter(this, cells.flatten())
        GameBoard.adapter = adapter
    }

    private fun revealCell(cell: Cell) {
        cell.faceUp = true
    }

    private fun placeBombs() {
        for (i in 0..9) {
            val row = Random.nextInt(0..9)
            val col = Random.nextInt(0..9)
            cells[row][col].isBomb = true
        }
    }

    private fun placeNumbers() {
        cells.forEach { row: Array<Cell> ->
            row.forEach { cell: Cell ->
                cell.adjacentBombCount = getAdjacentCells(cell).filter { adjacentCell: Cell ->
                    adjacentCell.isBomb
                }.size
            }
        }
    }

    private fun getAdjacentCells(cell:Cell) : List<Cell> {
        var adjacentCells : MutableList<Cell?> = mutableListOf<Cell?>()
        val row = cell.row
        val col = cell.col

        adjacentCells.add(cells.getOrNull(row - 1)?.getOrNull(col - 1))
        adjacentCells.add(cells.getOrNull(row - 1)?.getOrNull(col))
        adjacentCells.add(cells.getOrNull(row - 1)?.getOrNull(col + 1))
        adjacentCells.add(cells.getOrNull(row + 1)?.getOrNull(col - 1))
        adjacentCells.add(cells.getOrNull(row + 1)?.getOrNull(col))
        adjacentCells.add(cells.getOrNull(row + 1)?.getOrNull(col + 1))
        adjacentCells.add(cells.getOrNull(row)?.getOrNull(col - 1))
        adjacentCells.add(cells.getOrNull(row)?.getOrNull(col + 1))

        return adjacentCells.toList().filterNotNull()
    }
}