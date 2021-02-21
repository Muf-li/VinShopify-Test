package com.test.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.R
import com.test.models.BannerSliderModel
import com.test.models.CategoryModel
import com.test.models.FeaturedProductModel
import com.test.models.ResponseModel
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.squareup.picasso.Picasso
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject

class HomeFragment : Fragment() {

    private val bannerSliderArrayList: ArrayList<BannerSliderModel> = ArrayList()
    private val productList: ArrayList<FeaturedProductModel> = ArrayList()
    private val categoryList: ArrayList<CategoryModel> = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        initViews(root)
        return root
    }

    private fun initViews(view: View){
        val imgLogo: ImageView = view.findViewById(R.id.imgLogo)
        val spinner: Spinner = view.findViewById(R.id.spinnerRegion)

        val sliderView: SliderView = view.findViewById(R.id.imageSlider)
        val adapter = HomeSliderAdapter(requireContext(), bannerSliderArrayList)
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_RIGHT
        sliderView.scrollTimeInSec = 4
        sliderView.startAutoCycle()
        sliderView.setSliderAdapter(adapter)

        val rvProduct: RecyclerView = view.findViewById(R.id.featuredProductRecyclerView)
        val productAdapter = FeaturedProductAdapter(requireContext(), productList)
        rvProduct.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        rvProduct.itemAnimator = DefaultItemAnimator()
        rvProduct.adapter = productAdapter

        val rvCategory: RecyclerView = view.findViewById(R.id.categoryRecyclerView)
        val categoryAdapter = CategoryAdapter(requireContext(), categoryList)
        rvCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        rvCategory.itemAnimator = DefaultItemAnimator()
        rvCategory.adapter = categoryAdapter

        val client = AsyncHttpClient()
        val jsonParams = JSONObject()
        jsonParams.put("access_token", 752027)
        jsonParams.put("country_code", "SA")
        val entity = StringEntity(jsonParams.toString())

        client.post(context, "https://vinshopify.com/uat/api/home", entity, "application/json", object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header?>?, responseBody: ByteArray?) {
                val responStr = String(responseBody!!)
                Log.d("API", responStr)
                val gson = Gson()
                val model: ResponseModel = gson.fromJson(responStr, ResponseModel::class.java)
                Log.d("API", model.httpcode.toString())

                Picasso.with(context).load(model.data?.logo).into(imgLogo)
                bannerSliderArrayList.clear()

                model.data?.bannerSlider?.let { bannerSliderArrayList.addAll(it) }
                adapter.notifyDataSetChanged()

                model.data?.featured?.let { productList.addAll(it) }
                productAdapter.notifyDataSetChanged()

                model.data?.categories?.let { categoryList.addAll(it) }
                categoryAdapter.notifyDataSetChanged()

                val regions = ArrayList<String>()
                model.data?.regions?.forEach {
                    regions.add(it.countryName!!)
                }

                val spAdapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(),
                        android.R.layout.simple_spinner_item, regions.toTypedArray())
                spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = spAdapter
            }

            override fun onFailure(statusCode: Int, headers: Array<Header?>?, responseBody: ByteArray?, error: Throwable) {
                Log.e("API", "Response code: $statusCode")
            }
        })
    }
}