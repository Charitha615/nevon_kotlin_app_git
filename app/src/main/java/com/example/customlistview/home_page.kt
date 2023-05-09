package com.example.customlistview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class home_page : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val intent = this.intent
        val email = intent.getStringExtra("email")

        val user_email = findViewById<TextView>(R.id.textView2)
        val allProductButton = findViewById<Button>(R.id.all_product_button)
        user_email.setText(email)


        allProductButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

//    private fun getStudent() {
//        val stdList = dbHelper.getAllStudent()
//        Log.e("pppp","${stdList}")
//
//    }
}