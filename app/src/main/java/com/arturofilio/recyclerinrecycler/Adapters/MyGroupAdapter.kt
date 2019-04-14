package com.arturofilio.recyclerinrecycler.Adapters

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.arturofilio.recyclerinrecycler.Model.ItemGroup
import com.arturofilio.recyclerinrecycler.R

class MyGroupAdapter (private val context: Context,
                      private val dataList:List<ItemGroup>?): RecyclerView.Adapter<MyGroupAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_group,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList?.size?:0
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        myViewHolder.itemTitle.text = dataList!![position].headerTitle

        var items = dataList[position].listItem

        val itemListAdapter = MyItemAdapter(context, items)

        myViewHolder.recycler_view_list.setHasFixedSize(true)
        myViewHolder.recycler_view_list.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        myViewHolder.recycler_view_list.adapter = itemListAdapter

        myViewHolder.recycler_view_list.isNestedScrollingEnabled = false // Important
        myViewHolder.btnMore.setOnClickListener{
            Toast.makeText(context,"Btn More: " + dataList[position].headerTitle,Toast.LENGTH_SHORT).show()
        }
    }

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var itemTitle:TextView
        var recycler_view_list:RecyclerView
        var btnMore:Button

        init{
            itemTitle = view.findViewById(R.id.itemTitle) as TextView
            btnMore = view.findViewById(R.id.btnMore) as Button
            recycler_view_list = view.findViewById(R.id.my_recycler_view_list) as RecyclerView
        }
    }

}