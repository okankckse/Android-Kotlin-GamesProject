package com.okankocakose.appcentproject1.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.okankocakose.appcentproject1.DetailsActivity
import com.okankocakose.appcentproject1.Model.Result
import com.okankocakose.appcentproject1.databinding.RecyclerviewRow1Binding
import com.squareup.picasso.Picasso

class RecyclerViewAdapter1(var list : ArrayList<Result>) : RecyclerView.Adapter<RecyclerViewAdapter1.RecyclerView1Holder>(){

    class RecyclerView1Holder(val binding: RecyclerviewRow1Binding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView1Holder {
      val binding = RecyclerviewRow1Binding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerView1Holder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView1Holder, position: Int) {

        holder.binding.recyclerViewTextView.text = list.get(position).name
        holder.binding.textView.text= list.get(position).rating.toString()+ "  " + list.get(position).released
        Picasso.get().load(list.get(position).background_image).into(holder.binding.imageViewRecyclerView)

        holder.binding.textView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent : Intent = Intent(holder.itemView.context, DetailsActivity::class.java)
                intent.putExtra("id",list.get(position).id)
                holder.itemView.context.startActivity(intent)
            }
        })

        holder.binding.recyclerViewTextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent : Intent = Intent(holder.itemView.context, DetailsActivity::class.java)
                intent.putExtra("id",list.get(position).id)
                holder.itemView.context.startActivity(intent)
            }
        })


    }

    override fun getItemCount(): Int {
        return list.size
    }

     fun filterList (filteredList : ArrayList<Result>) {
         list = filteredList
         notifyDataSetChanged()
     }
}