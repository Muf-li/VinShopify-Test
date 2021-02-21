package com.test.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.test.R
import com.test.models.BannerSliderModel
import com.test.ui.home.HomeSliderAdapter.SliderAdapterVH
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso
import java.util.*

class HomeSliderAdapter(private val context: Context, private val bannerSliderModelArrayList: ArrayList<BannerSliderModel>) : SliderViewAdapter<SliderAdapterVH>() {

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_home_slider, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val banner = bannerSliderModelArrayList[position]
        Picasso.with(context).load(banner.sliderImage).into(viewHolder.imgSlider)
    }

    override fun getCount(): Int {
        return bannerSliderModelArrayList.size
    }

    class SliderAdapterVH(var itemView: View) : ViewHolder(itemView) {
        var imgSlider: ImageView = itemView.findViewById(R.id.imgSlider)
    }
}