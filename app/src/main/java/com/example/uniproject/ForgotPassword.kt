package com.example.uniproject

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPassword: AppCompatActivity() {

    private lateinit var forgotPasswordGoBackButton : ImageView
    private lateinit var editTextTextEmailAddress: EditText
    private lateinit var textViewsendPassBtn: TextView

    private var firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgotpasswordactivity)

        init()
        ForgetPasswordListeners()
    }

    private fun ForgetPasswordListeners() {
        forgotPasswordGoBackButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        textViewsendPassBtn.setOnClickListener {
            val email = editTextTextEmailAddress.text.toString()

            if(email.isEmpty()){
                Toast.makeText(this, "email is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener() {task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "we sent your password to your email", Toast.LENGTH_SHORT).show()
                    finish()
                }else {
                    Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun init() {
        forgotPasswordGoBackButton = findViewById(R.id.forgotPasswordGoBackButton)
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)
        textViewsendPassBtn = findViewById(R.id.textViewsendPassBtn)
    }
}