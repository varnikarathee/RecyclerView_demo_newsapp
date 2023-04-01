package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class NewsListAdapter( private val listener:NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items:ArrayList<News> =ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent, false)
        val holder= NewsViewHolder(view)
        view.setOnClickListener(){
            listener.OnItemClicked(items[holder.absoluteAdapterPosition])
        }
         return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
       val currentItem=items[position]
        holder.titleView.text=currentItem.title

    }

    fun updatedNews(updateNews: ArrayList<News>){
        items.clear()
        items.addAll(updateNews)
        notifyDataSetChanged()
    }

}

class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val titleView: TextView=itemView.findViewById(R.id.title)


}

interface NewsItemClicked{
    fun OnItemClicked(item:News)
}