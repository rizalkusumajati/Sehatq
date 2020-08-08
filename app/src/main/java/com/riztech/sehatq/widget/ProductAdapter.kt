package com.riztech.sehatq.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.riztech.sehatq.R
import com.riztech.sehatq.model.Product
import com.riztech.sehatq.util.getProgressDrawable
import com.riztech.sehatq.util.loadImage

class ProductAdapter (private val context: Context?, private val productList: ArrayList<Product>, private val listener: ProductClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_product, parent,false)
        return ProductHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateListData(newListData : ArrayList<Product>){
        productList.clear()
        productList.addAll(newListData)
        notifyDataSetChanged()
    }

    interface ProductClickListener{
        fun clickProduct(product: Product)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val productItem = productList.get(position)
        when(holder){
            is ProductHolder -> holder.bind(productItem)
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }

    inner class ProductHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleProduct: TextView = itemView.findViewById(R.id.tvProductTitleItem)
        val imageProduct: ImageView = itemView.findViewById(R.id.ivProductImageItem)
        val price: TextView = itemView.findViewById(R.id.tvProductPriceItem)
        fun bind(item: Product) {
            titleProduct.text = item.title
            if (item.imageUrl.equals("default")){
                imageProduct.loadImage(R.drawable.ic_product, getProgressDrawable(itemView.context))
            }else {
                imageProduct.loadImage(item.imageUrl, getProgressDrawable(itemView.context))
            }
            price.text = item.price
            itemView.setOnClickListener {
                listener.clickProduct(item)
//                    val action = HomeFragment.goToDetailLink(item.link)
//                    Navigation.findNavController(itemView).navigate(action)

            }
        }
    }
}