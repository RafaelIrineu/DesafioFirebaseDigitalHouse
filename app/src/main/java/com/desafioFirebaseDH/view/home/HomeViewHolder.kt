package com.desafioFirebaseDH.view.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.desafioFirebaseDH.R
import com.desafioFirebaseDH.model.Game
import com.squareup.picasso.Picasso

class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val _imagem = view.findViewById<ImageView>(R.id.imgCard)
    private val _nomeDoJogo = view.findViewById<TextView>(R.id.txtNome)
    private val _anoDoJogo = view.findViewById<TextView>(R.id.txtAno)

    fun bind(game: Game) {
        //carregarImagem(game.imagem, _imagem)
        _nomeDoJogo.text = game.nome
        _anoDoJogo.text = game.ano
    }

    fun carregarImagem(nomeDaImagem: String, imagemContainer: ImageView?) {
        //val storage = FirebaseStorage.getInstance().getReference("uploads")

//        storage.child(nomeDaImagem).downloadUrl.addOnSuccessListener {
//            Picasso.get()
//                .load(it)
//                .into(imagemContainer)
    }
}