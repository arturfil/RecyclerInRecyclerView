package com.arturofilio.recyclerinrecycler.Adapters

import android.content.Context
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.arturofilio.recyclerinrecycler.Interface.IItemClickListener
import com.arturofilio.recyclerinrecycler.Model.ItemData
import com.arturofilio.recyclerinrecycler.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_item.view.*

class MyItemAdapter(private val context: Context,
                    private val itemList:List<ItemData>?): RecyclerView.Adapter<MyItemAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_item,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList?.size ?:0
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        myViewHolder.txt_title.setText(itemList!![position].name!!)
        Picasso.get().load(itemList[position].image).into(myViewHolder.img_item)

        myViewHolder.setClick(object :IItemClickListener {
            override fun onItemClickListener(view: View, position: Int) {
                Toast.makeText(context,""+ itemList[position].name,Toast.LENGTH_SHORT).show()
            }

        })
    }

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener {

        var txt_title:TextView
        var img_item: ImageView

        lateinit var iItemClickListener:IItemClickListener

        fun setClick(iItemClickListener: IItemClickListener) {
            this.iItemClickListener = iItemClickListener
        }

        init{
            txt_title = view.findViewById(R.id.txt_Title) as TextView
            img_item = view.findViewById(R.id.itemImage) as ImageView

            view.setOnClickListener(this )
        }

        override fun onClick(view: View?) {
            iItemClickListener.onItemClickListener(view!!,adapterPosition)
        }

    }
}