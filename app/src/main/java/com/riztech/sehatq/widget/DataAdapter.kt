package com.riztech.sehatq.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.riztech.sehatq.R
import com.riztech.sehatq.model.Category
import com.riztech.sehatq.model.Product
import com.riztech.sehatq.util.getProgressDrawable
import com.riztech.sehatq.util.loadImage

class DataAdapter(private val context: Context?, private var adapterList: ArrayList<Any>, private val listener: ProductClickListener) : RecyclerView.Adapter<BaseViewHolder<*>> () {

    companion object{
        private const val VIEW_CATEGORY = 0;
        private const val VIEW_PRODUCT = 1;
        private const val VIEW_ARTICLE = 2;
    }

    fun updateListData(newListData : ArrayList<Any>){
        adapterList.clear()
        adapterList.addAll(newListData)
        notifyDataSetChanged()
    }

    interface ProductClickListener{
        fun clickProduct(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when(viewType){
            VIEW_CATEGORY -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_category, parent,false)
                CategoryViewHolder(view)
            }
            VIEW_PRODUCT -> {
                val view = LayoutInflater.from(context).inflate(R.layout.product_item, parent,false)
                ProductViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")

        }
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = adapterList[position]
        when(holder) {
            is CategoryViewHolder -> holder.bind(element as ArrayList<Category>)
            is ProductViewHolder -> holder.bind(element as Product)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
              0 -> VIEW_CATEGORY
              else -> VIEW_PRODUCT
        }
    }

    inner class CategoryViewHolder(itemView : View): BaseViewHolder<ArrayList<Category>>(itemView){
        val gridView: RecyclerView = itemView.findViewById(R.id.rvCategory)
        private lateinit var adapterProduct: CategoryAdapter

        override fun bind(item: ArrayList<Category>) {

            adapterProduct = CategoryAdapter(context, item)

            gridView.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = adapterProduct
                setHasFixedSize(true)
            }


        }

    }

    inner class ProductViewHolder(itemView: View) : BaseViewHolder<Product>(itemView){
        val titleProduct: TextView = itemView.findViewById(R.id.tvProductTitle)
        val imageProduct: ImageView = itemView.findViewById(R.id.ivProductImage)
        val price: TextView = itemView.findViewById(R.id.tvProductPrice)
        val love: ImageView = itemView.findViewById(R.id.love)
        val likes: TextView = itemView.findViewById(R.id.likes)

        override fun bind(item: Product) {
            titleProduct.text = item.title
            price.text = item.price
            imageProduct.loadImage(item.imageUrl, getProgressDrawable(itemView.context))
            item.loved?.let {
                if (it > 0){
                    likes.text = "$it like(s)"
                }
            }

            titleProduct.setOnClickListener {
                listener.clickProduct(item)
            }

            imageProduct.setOnClickListener{
                listener.clickProduct(item)
            }

            price.setOnClickListener {
                listener.clickProduct(item)
            }

            love.setOnClickListener {
                love.setImageResource(R.drawable.ic_heart_clicked)
                likes.text = "${item.loved?.plus(1)} like(s)"
            }

        }
    }
}