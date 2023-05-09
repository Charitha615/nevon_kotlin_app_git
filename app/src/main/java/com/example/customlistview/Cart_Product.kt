package com.example.customlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Cart_Product : AppCompatActivity() {

    private lateinit var edName : EditText
    private lateinit var edEmail : EditText
    private lateinit var btnAdd : Button
    private lateinit var btnView : Button
    private lateinit var btnUpdate : Button

    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: CartAdapter? =null
    private var std: CartModel? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_product)

//        val intent = this.intent
//        val ItemID = intent.getStringExtra("itemID")
//
//        edName.setText(ItemID)

        initView()
        initRecyclerView()

        sqLiteHelper = SQLiteHelper(this)

        btnAdd.setOnClickListener{addStudent()}
        btnView.setOnClickListener{getStudent()}
        btnUpdate.setOnClickListener{updateStudent()}


        adapter?.setOnClickItem {
            Toast.makeText(this,it.name, Toast.LENGTH_SHORT).show()
            //Update

            edName.setText(it.name)
            edEmail.setText(it.email)
            std = it
        }

        adapter?. setOnClickDeleteItem {
            deleteStudent(it.id)
        }
    }

    private fun updateStudent() {
        var name = edName.text.toString()
        var email = edEmail.text.toString()

        //Check Record
        if(name == std?.name && email == std?.email){
            Toast.makeText(this,"Record Not Changed....", Toast.LENGTH_SHORT).show()
            return
        }

        if (std == null)return

        val std = CartModel(id = std!!.id, name = name, email=email)
        val status = sqLiteHelper.updateStudent(std)
        if(status > -1){

            clearEditText()
            getStudent()
        }else{
            Toast.makeText(this,"Update Failed.....", Toast.LENGTH_SHORT).show()

        }
    }

    private fun getStudent() {
        val stdList = sqLiteHelper.getAllStudent()
        Log.e("pppp","${stdList.size}")

        //

        adapter?.addItems(stdList)
    }

    private fun addStudent() {
        var name = edName.text.toString()
        var email = edEmail.text.toString()

        if(name.isEmpty() || email.isEmpty()){
            Toast.makeText(this,"Please enter requried field", Toast.LENGTH_SHORT).show()
        }else{
            val std = CartModel(name = name , email = email)
            val status = sqLiteHelper.insertStudent(std)
            // Check insert success or not

            if(status > -1){
                Toast.makeText(this,"Student Added.....", Toast.LENGTH_SHORT).show()
                clearEditText()
            }else{
                Toast.makeText(this,"Record not saved!..", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun deleteStudent(id: Int){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete item? ")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes"){ dialog, _ ->
            sqLiteHelper.deleteStudentById(id)
            getStudent()
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){ dialog, _ ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }


    private fun clearEditText() {
        edName.setText("")
        edEmail.setText("")
        edName.requestFocus()
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CartAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView() {
        edName = findViewById(R.id.eName)
        edEmail = findViewById(R.id.eEmail)
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)
        btnUpdate = findViewById(R.id.btnUpdate)
        recyclerView = findViewById(R.id.recyclerView)
    }
}