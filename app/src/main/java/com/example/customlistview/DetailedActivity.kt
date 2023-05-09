package com.example.customlistview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.customlistview.databinding.ActivityDetailedBinding

class DetailedActivity : AppCompatActivity() {

    private lateinit var btnAdd : Button
    private lateinit var ItemID : TextView
    private lateinit var binding: ActivityDetailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = this.intent
        if (intent != null) {
            val name = intent.getStringExtra("name")
            val time = intent.getStringExtra("time")
            val ingredients = intent.getIntExtra("ingredients", R.string.maggiIngredients)
            val desc = intent.getIntExtra("desc", R.string.maggieDesc)
            val image = intent.getIntExtra("image", R.drawable.maggi)
            binding.detailName.text = name
            binding.detailTime.text = time
            binding.detailDesc.setText(desc)
//            binding.detailIngredients.setText(ingredients)
            binding.detailImage.setImageResource(image)
        }
        btnAdd = findViewById(R.id.add_to_cart_btn);
        ItemID = findViewById(R.id.itemID);

        btnAdd.setOnClickListener {
            val intent = Intent(this, Cart_Product::class.java)
            intent.putExtra("itemID",ItemID.text)
            startActivity(intent)
        }
    }
}