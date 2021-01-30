package com.desafioFirebaseDH.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.desafioFirebaseDH.R
import com.desafioFirebaseDH.model.Game
import com.desafioFirebaseDH.view.CreateGameActivity
import com.desafioFirebaseDH.view.CreateGameActivity.Companion.ANO
import com.desafioFirebaseDH.view.CreateGameActivity.Companion.DESCRIPTION
import com.desafioFirebaseDH.view.CreateGameActivity.Companion.IMAGEM
import com.desafioFirebaseDH.view.CreateGameActivity.Companion.NOME
import com.desafioFirebaseDH.view.DetailActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class HomeActivity : AppCompatActivity() {

    data class GameReviewObjectModel (
        var imageUri: String = "",
        var name: String = "",
        var createdAt: String = "",
        var description: String = ""
    )

    data class GameReviewModel(
        var createdAt: String = "",
        var description: String = "",
        var id: String = "",
        var name: String = ""
    )

    private val btnAdd by lazy {  findViewById<FloatingActionButton>(R.id.btnAddJogo) }
    private val listaDeJogos = mutableListOf<GameReviewObjectModel>()
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.listaRecyclerView)}
    private val viewManager = GridLayoutManager(this,2)

    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("game-review")
    private val gameIdsRef = database.getReference("game-id")
    private val myStorage = FirebaseStorage.getInstance().getReference("game-review-picture")
    private var user = FirebaseAuth.getInstance().currentUser!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getIds()

        Handler(Looper.getMainLooper()).postDelayed({
            val _listAdapter = HomeAdapter(listaDeJogos) {
                val intent = Intent(this@HomeActivity, DetailActivity::class.java)

                intent.putExtra(NOME, it.name)
                intent.putExtra(IMAGEM, it.imageUri)
                intent.putExtra(DESCRIPTION, it.description)
                intent.putExtra(ANO, it.createdAt)

                startActivity(intent)
            }

            recyclerView.apply {
                layoutManager = viewManager
                adapter = _listAdapter
                setHasFixedSize(true)
            }
        }, 2000)

        btnAdd.setOnClickListener {
            val intent = Intent(this, CreateGameActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getIds(){

        gameIdsRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue(CreateGameActivity.Post::class.java)

                value?.list?.forEach {
                    myRef.child(it).addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val value = dataSnapshot.getValue(GameReviewModel::class.java)!!
                            myStorage.child(value.id).downloadUrl.addOnSuccessListener {
                                listaDeJogos.add(
                                    GameReviewObjectModel(
                                        it.toString(),
                                        value.name,
                                        value.createdAt,
                                        value.description
                                    )
                                )
                            }
                        }
                        override fun onCancelled(error: DatabaseError) {}
                    })
                }
            }
        })
    }
}