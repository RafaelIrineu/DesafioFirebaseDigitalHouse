package com.desafioFirebaseDH.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.desafioFirebaseDH.R
import com.desafioFirebaseDH.view.CreateGameActivity.Companion.ANO
import com.desafioFirebaseDH.view.CreateGameActivity.Companion.DESCRIPTION
import com.desafioFirebaseDH.view.CreateGameActivity.Companion.IMAGEM
import com.desafioFirebaseDH.view.CreateGameActivity.Companion.NOME
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    private val _title1 by lazy { findViewById<TextView>(R.id.txt_title_detail_1) }
    private val _title2 by lazy { findViewById<TextView>(R.id.txt_title_detail_2) }
    private val _image by lazy { findViewById<ImageView>(R.id.img_tumbnail_detail) }
    private val _createdAt by lazy { findViewById<TextView>(R.id.txt_createdAt_detail) }
    private val _description by lazy { findViewById<TextView>(R.id.txt_description_detail) }
    private lateinit var _bundleTitle: String
    private lateinit var _bundleImageUrl: String
    private lateinit var _bundleCreatedAt: String
    private lateinit var _bundleDescription: String
    private val _arrowBack by lazy { findViewById<ImageView>(R.id.arrowBack_details) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        _arrowBack.setOnClickListener {
            finish()
        }

        _bundleTitle = intent.getStringExtra(NOME).toString()
        _bundleImageUrl = intent.getStringExtra(IMAGEM).toString()
        _bundleCreatedAt = intent.getStringExtra(ANO).toString()
        _bundleDescription = intent.getStringExtra(DESCRIPTION).toString()

        _title1.text = _bundleTitle
        _title2.text = _bundleTitle
        _createdAt.text = _bundleCreatedAt
        _description.text = _bundleDescription
    }
}