package com.desafioFirebaseDH.view.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.desafioFirebaseDH.R

class HomeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val _imagem by lazy { view.findViewById<ImageView>(R.id.imgCard) }
    private val _nomeDoJogo by lazy { view.findViewById<TextView>(R.id.txtNome) }
    private val _anoDoJogo by lazy { view.findViewById<TextView>(R.id.txtAno) }

    fun bind(game: HomeActivity.GameReviewObjectModel) {
        //carregarImagem(game.imagem, _imagem)
        _nomeDoJogo.text = game.name
        _anoDoJogo.text = game.createdAt
    }
}
