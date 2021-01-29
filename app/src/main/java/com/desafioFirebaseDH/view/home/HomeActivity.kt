package com.desafioFirebaseDH.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.desafioFirebaseDH.R
import com.desafioFirebaseDH.model.Game

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val recyclerView = findViewById<RecyclerView>(R.id.listaRecyclerView)
        val viewManager = GridLayoutManager(this,2)
        val viewAdapter = HomeAdapter(mutableListOf())

        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}