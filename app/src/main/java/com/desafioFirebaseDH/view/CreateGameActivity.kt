package com.desafioFirebaseDH.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.desafioFirebaseDH.R
import com.desafioFirebaseDH.model.Game
import com.desafioFirebaseDH.view.home.HomeActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class CreateGameActivity : AppCompatActivity() {

    data class Post(val list: MutableList<String> = mutableListOf())

    private val nome by lazy { findViewById<EditText>(R.id.gameEditText) }
    private val ano by lazy { findViewById<EditText>(R.id.createdAtEditText) }
    private val description by lazy { findViewById<EditText>(R.id.descriptionEditText) }
    private val imagem by lazy { findViewById<ImageView>(R.id.imagemDoJogo) }
    private val btnSalvar by lazy { findViewById<Button>(R.id.btnSalvarGame) }
    private val imagemCamera by lazy { findViewById<ImageView>(R.id.image_camera_add) }
    private var validadorImagem: Boolean = false
    private var user = FirebaseAuth.getInstance().currentUser!!
    private val database = FirebaseDatabase.getInstance()
    private val gameReviewRef = database.getReference("game-review")
    private val gameIdsRef = database.getReference("game-id")
    private val myStorage = FirebaseStorage.getInstance().getReference("game-review-picture")

    private var bundleNome: String? = null
    private var bundleAno: String? = null
    private var bundleDescription: String? = null
    private var bundleImagem: String? = null
    private var _imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)

        bundleNome = intent.getStringExtra(NOME)
        bundleAno = intent.getStringExtra(ANO)
        bundleDescription = intent.getStringExtra(DESCRIPTION)
        bundleImagem = intent.getStringExtra(IMAGEM)

        imagem.setOnClickListener {
            findFile()
        }

        nullValidation()

        nome.setText(bundleNome)
        ano.setText(bundleAno)
        description.setText(bundleDescription)

        btnSalvar.setOnClickListener {
            if (nome.text.isNullOrEmpty()) {
                nome.error = getString(R.string.campo_vazio)
            }
            if (ano.text.isNullOrEmpty()) {
                ano.error = getString(R.string.campo_vazio)
            }
            if (description.text.isNullOrEmpty()) {
                description.error = getString(R.string.campo_vazio)
            }
            if (!validadorImagem) {
                Snackbar.make(
                    it, "Replace a picture!!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            if (!nome.text.isNullOrEmpty() &&
                !ano.text.isNullOrEmpty() &&
                !description.text.isNullOrEmpty() &&
                validadorImagem
            ) {
                val newReview = Game(
                    "${user.uid}${nome.text}",
                    nome.text.toString(),
                    ano.text.toString(),
                    description.text.toString()
                )

                gameReviewRef.child(newReview.id).setValue(newReview)
                creatIdList(newReview)
                upload(newReview.id)
                toHome()
            }
        }
    }

    private fun creatIdList(newReview: Game) {
        gameIdsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue(Post::class.java)
                val newPost = Post()

                value?.list?.forEach { string ->
                    newPost.list.add(string)
                }

                newPost.list.add(newReview.id)

                gameIdsRef.setValue(newPost)
            }
        })
    }

    private fun upload(name: String) {

        _imageUri?.run {
            myStorage.child(name).putFile(this)
                .addOnSuccessListener {
                    Log.d("FIREBASE_PIC", myStorage.toString())
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this@CreateGameActivity,
                        "ERROR: Upload Picture!!",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CONTEXT_RESQUEST_CODE && resultCode == RESULT_OK) {
            _imageUri = data?.data
            imagemCamera.animate().alpha(0f)
            validadorImagem = true
            Picasso.get().load(_imageUri).into(imagem)
        }
    }

    private fun toHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun findFile() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, CONTEXT_RESQUEST_CODE)
    }

    private fun nullValidation() {
        if (bundleNome == null) {
            bundleNome = ""
        }
        if (bundleAno == null) {
            bundleNome = ""
        }
        if (bundleDescription == null) {
            bundleNome = ""
        }
        if (bundleImagem != null) {
            imagemCamera.alpha = 0f
            validadorImagem = true
            Picasso.get().load(bundleImagem).into(imagem)
        }
    }

    companion object {
        const val CONTEXT_RESQUEST_CODE = 1
        const val NOME = "NOME"
        const val IMAGEM = "IMAGEM"
        const val ANO = "CREATED_AT"
        const val DESCRIPTION = "DESCRIPTION"
    }
}