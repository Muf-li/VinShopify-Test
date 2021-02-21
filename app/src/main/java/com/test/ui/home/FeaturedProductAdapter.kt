package com.test.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.R
import com.test.models.FeaturedProductModel
import com.squareup.picasso.Picasso
import java.util.*

class FeaturedProductAdapter(private val context: Context, private val featuredProductModelArrayList: ArrayList<FeaturedProductModel>)
    : RecyclerView.Adapter<FeaturedProductAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_featured_product, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val product = featuredProductModelArrayList[position]
        Picasso.with(context).load(product.prdImage).into(holder.imgProduct)
        holder.tvProductName.text = product.prdName
        holder.tvQty.text = product.qty
        holder.tvPrice.text = product.price +" "+ product.currency

        if(product.rating.isNullOrEmpty()){
            holder.ratingBar.rating = 0f
        } else{
            holder.ratingBar.rating = product.rating!!.toFloat()
        }
    }

    override fun getItemCount(): Int {
        return featuredProductModelArrayList.size
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgProduct: ImageView = itemView.findViewById(R.id.imgProduct)
        var tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        var tvQty: TextView = itemView.findViewById(R.id.tvQty)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        var ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
    }
}