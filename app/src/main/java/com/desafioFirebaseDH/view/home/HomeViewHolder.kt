package com.desafioFirebaseDH.view.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.desafioFirebaseDH.R

class HomeViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val _imagem = view.findViewById<ImageView>(R.id.imgCard)
    private val _nomeDoJogo = view.findViewById<TextView>(R.id.txtNome)
    private val _anoDoJogo = view.findViewById<TextView>(R.id.txtAno)

}