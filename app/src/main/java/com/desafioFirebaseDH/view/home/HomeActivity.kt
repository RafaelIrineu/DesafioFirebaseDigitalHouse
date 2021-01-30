package com.desafioFirebaseDH.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.desafioFirebaseDH.R
import com.desafioFirebaseDH.model.Game
import com.desafioFirebaseDH.view.CreateGameActivity
import com.desafioFirebaseDH.view.DetailActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    private val btnAdd by lazy {  findViewById<FloatingActionButton>(R.id.btnAddJogo) }
    private val listaDeJogos = mutableListOf<Game>()
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.listaRecyclerView)}
    private val viewManager = GridLayoutManager(this,2)

    private val viewAdapter = HomeAdapter(listaDeJogos){
        val intent = Intent(this, DetailActivity::class.java)

        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        listaFake(8)

        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        btnAdd.setOnClickListener {
            val intent = Intent(this, CreateGameActivity::class.java)
            startActivity(intent)
        }
    }

    private fun listaFake(amount: Int) {

        for (i in 1..amount) {
            listaDeJogos.add(
                Game(
                    "Mortal Kombat",
                    "2021",
                    "ic_launcer",
                )
            )
        }

    }

}