package com.example.api

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.round

class MyRecyclerAdapter :androidx.recyclerview.widget.ListAdapter<Weather, RecyclerView.ViewHolder>(
    WeatherDiffCallBack()
) {

    val KALVIN_NULL = -273.15;

    class WeatherDiffCallBack : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem.dt_txt == newItem.dt_txt
        }

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }

    }


    class ViewHolderHot (itemView : View) : RecyclerView.ViewHolder (itemView) {
        val date : TextView = itemView.findViewById(R.id.text_time)
        val grad : TextView = itemView.findViewById(R.id.text_grad)

        @SuppressLint("SetTextI18n")
        fun bindTo(weather: Weather){
            date.text = weather.dt_txt
            grad.text = "${round(weather.main.temp - 273)}°С"
        }

    }

    class ViewHolderCold (itemView : View) : RecyclerView.ViewHolder (itemView) {
        val date : TextView = itemView.findViewById(R.id.text_time)
        val grad : TextView = itemView.findViewById(R.id.text_grad)

        @SuppressLint("SetTextI18n")
        fun bindTo(weather: Weather){
            date.text = weather.dt_txt
            grad.text = "${round(weather.main.temp - 273)}°С"
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).main.temp+KALVIN_NULL>0) R.layout.item_view_hot else R.layout.item_view_cold
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent,false)
        return if (viewType == R.layout.item_view_hot) ViewHolderHot(view) else ViewHolderCold(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        if (holder is ViewHolderCold) {holder.bindTo(data)} else if (holder is ViewHolderHot) {holder.bindTo(data)}
    }


}