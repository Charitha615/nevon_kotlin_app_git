package com.example.customlistview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Hair_Styles_Women : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hair_styles_women)

        val men_hairStyle_Btn = findViewById<Button>(R.id.men_hairStyle_Btn)


        men_hairStyle_Btn.setOnClickListener {
            val intent = Intent(this, Hair_Styles_Men::class.java)
            startActivity(intent)
        }
    }
}