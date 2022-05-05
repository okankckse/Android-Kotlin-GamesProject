package com.okankocakose.appcentproject1.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.okankocakose.appcentproject1.databinding.RecyclerviewRow2Binding

class RecyclerViewAdapter2(val list: ArrayList<String>) : RecyclerView.Adapter<RecyclerViewAdapter2.RecyclerView2Holder>() {

    class RecyclerView2Holder(val binding: RecyclerviewRow2Binding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView2Holder {
        val binding = RecyclerviewRow2Binding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerView2Holder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView2Holder, position: Int) {
       holder.binding.recyclerView2Name.text = list.get(position)
    }

    override fun getItemCount(): Int {
       return list.size
    }
}