package com.desafioFirebaseDH.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.desafioFirebaseDH.R

class HomeAdapter(private val _lista: MutableList<HomeActivity.GameReviewObjectModel>,
                  private val _listener: (HomeActivity.GameReviewObjectModel) -> Unit): RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lista, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount() = _lista.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = _lista[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { _listener(item) }
    }
}