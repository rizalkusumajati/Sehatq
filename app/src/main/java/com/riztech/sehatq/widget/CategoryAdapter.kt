package com.riztech.sehatq.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.riztech.sehatq.R
import com.riztech.sehatq.model.Category
import com.riztech.sehatq.model.Product
import com.riztech.sehatq.util.getProgressDrawable
import com.riztech.sehatq.util.loadImage

class CategoryAdapter (private val context: Context?, private val categoryList: ArrayList<Category>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.category_item, parent,false)
            return CategoryHolder(view)
        }

    fun updateListData(newListData : ArrayList<Category>){
        categoryList.clear()
        categoryList.addAll(newListData)
        notifyDataSetChanged()
    }

        override fun getItemCount(): Int {
            return categoryList.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val productItem = categoryList.get(position)
            when(holder){
                is CategoryHolder -> holder.bind(productItem)
                else -> throw IllegalArgumentException("Invalid type of data " + position)
            }
        }

        inner class CategoryHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
            val ivIcon: ImageView = itemView.findViewById(R.id.ivIconMenu)

            fun bind(item: Category){
                tvCategory.text = item.name
                ivIcon.loadImage(item.imageUrl, getProgressDrawable(itemView.context))

            }
        }
}