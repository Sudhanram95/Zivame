package com.example.zivame_assignment.ui.gadgetlist.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zivame_assignment.R
import com.example.zivame_assignment.ui.gadgetlist.model.ProductModel

class GadgetAdapter(val context: Context,
                    val productList: List<ProductModel>) : RecyclerView.Adapter<GadgetAdapter.Companion.MyViewHolder>() {

    companion object {
        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val ratingBar: RatingBar
            val imgProduct: ImageView
            val txtProductName: TextView
            val btnAddToCart: Button
            init {
                ratingBar = view.findViewById(R.id.ratingProduct)
                imgProduct = view.findViewById(R.id.imgProduct)
                txtProductName = view.findViewById(R.id.txtName)
                btnAddToCart = view.findViewById(R.id.btnAddToCart)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        val myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {

        val productDetail = productList.get(position)

        holder.ratingBar.rating = productDetail.rating

        Glide.with(context)
            .load(productDetail.imageUrl)
            .into(holder.imgProduct)

        holder.txtProductName.text = productDetail.name

        holder.btnAddToCart.setOnClickListener {
            Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()
        }
    }
}