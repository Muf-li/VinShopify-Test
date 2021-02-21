package com.test.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.R
import com.test.models.CategoryModel
import com.squareup.picasso.Picasso
import java.util.*

class CategoryAdapter(private val context: Context, private val categoryList: ArrayList<CategoryModel>)
    : RecyclerView.Adapter<CategoryAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val category = categoryList[position]
        Picasso.with(context).load(category.categoryImage).into(holder.imgCategory)
        holder.tvCategory.text = category.categoryName
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgCategory: ImageView = itemView.findViewById(R.id.imgCategory)
        var tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
    }
}