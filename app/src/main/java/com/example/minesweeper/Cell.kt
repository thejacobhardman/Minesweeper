package com.example.minesweeper

class Cell (var faceUp: Boolean, val row: Int, val col: Int) {

    var isBomb = false
    var adjacentBombCount = 0

    fun returnImage() : Int {
        if (faceUp) {
            if (!isBomb) {
                when (adjacentBombCount) {
                    0 -> return R.drawable.img_0
                    1 -> return R.drawable.img_1
                    2 -> return R.drawable.img_2
                    3 -> return R.drawable.img_3
                    4 -> return R.drawable.img_4
                    5 -> return R.drawable.img_5
                    6-> return R.drawable.img_6
                    7 -> return R.drawable.img_7
                    8 -> return R.drawable.img_8
                }
            }
            else {
                return R.drawable.bomb
            }
        }
        return R.drawable.facing_down
    }
}