package com.example.customlistview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Hair_Styles_Men : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hair_styles_men)


        val women_hairStyle_Btn = findViewById<Button>(R.id.women_hairStyle_Btn)


        women_hairStyle_Btn.setOnClickListener {
            val intent = Intent(this, Hair_Styles_Women::class.java)
            startActivity(intent)
        }
    }
}