package com.example.uniproject

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AskInfoActivity: AppCompatActivity() {
    private lateinit var editTextTextNameandlasntname: EditText
    private lateinit var editTextWorkEdu: EditText
    private lateinit var editTextaxalwlebzerakeni: EditText
    private lateinit var textViewinfoChanges: TextView
    private lateinit var editTextAge: EditText
    private lateinit var forgotPasswordGoBackButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.askinfoactivity)

        init()
        askInfoListeners()
    }

    private fun askInfoListeners(){
        forgotPasswordGoBackButton.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        textViewinfoChanges.setOnClickListener {
            val nameLastname = editTextTextNameandlasntname.text.toString()
            val workEdu = editTextWorkEdu.text.toString()
            val axaliWlebi = editTextaxalwlebzerakeni.text.toString()
            val age = editTextAge.text.toString().toInt()

            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("nameLastname",nameLastname)
            intent.putExtra("age",age)
            intent.putExtra("workEdu",workEdu)
            intent.putExtra("axaliWlebi",axaliWlebi)

            startActivity(intent)

        }
    }



    private fun init(){
        editTextTextNameandlasntname = findViewById(R.id.editTextTextNameandlasntname)
        editTextWorkEdu = findViewById(R.id.editTextWorkEdu)
        editTextaxalwlebzerakeni = findViewById(R.id.editTextaxalwlebzerakeni)
        textViewinfoChanges = findViewById(R.id.textViewinfoChanges)
        editTextAge = findViewById(R.id.editTextAge)
        forgotPasswordGoBackButton = findViewById(R.id.forgotPasswordGoBackButton)
    }

}