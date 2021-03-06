package com.example.zivame_assignment.ui.cart.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zivame_assignment.R
import com.example.zivame_assignment.database.CartEntity

class CartAdapter(val context: Context,
                  val cartList: MutableList<CartEntity>) : RecyclerView.Adapter<CartAdapter.Companion.MyViewHolder>() {

    companion object {
        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imgItem: ImageView
            val txtProductName: TextView
            val txtProductPrice: TextView
            val txtRemove: TextView
            init {
                imgItem = view.findViewById(R.id.img_item)
                txtProductName = view.findViewById(R.id.txt_product_name)
                txtProductPrice = view.findViewById(R.id.txt_item_price)
                txtRemove = view.findViewById(R.id.txt_remove)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        val myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {

        val cartEntity = cartList.get(position)

        Glide.with(context)
            .load(cartEntity.imageUrl)
            .into(holder.imgItem)

        holder.txtProductName.text = cartEntity.itemName
        holder.txtProductPrice.text = "Rs. ${cartEntity.price}"

        holder.txtRemove.setOnClickListener {

        }
    }

    fun removeItemFromCart(position: Int, cartEntity: CartEntity) {
        cartList.remove(cartEntity)
        notifyItemRemoved(position)
    }
}