package com.example.minesweeper

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class GridAdapter (private val context: Context, private val cells: List<Cell>) : BaseAdapter() {
    override fun getCount(): Int {
        return cells.size
    }

    override fun getItem(position: Int): Any {
        return cells.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View? = convertView
        var holder: ViewHolder

        if (view == null) {
            val inflater:LayoutInflater = (context as Activity).layoutInflater
            view = inflater.inflate(R.layout.grid_item, parent, false)
            holder = ViewHolder()
            holder.imageView = view!!.findViewById<ImageView>(R.id.cell) as ImageView
            view.setTag(holder)
        } else {
            holder = view.getTag() as ViewHolder
        }
        holder.imageView!!.setImageResource(cells[position].returnImage())
        return view
    }

    class ViewHolder {
        var imageView: ImageView? = null
    }
}