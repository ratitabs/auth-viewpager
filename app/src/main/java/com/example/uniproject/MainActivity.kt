package com.example.uniproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity: AppCompatActivity() {
    private lateinit var editTextTextEmailAddress: EditText
    private lateinit var editTextTextPassword : EditText
    private lateinit var textViewSignInBtn: TextView
    private lateinit var textViewForgotPassword: TextView
    private lateinit var textViewDontHaveAcc: TextView

    private val firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        signInListeners()
    }



    private fun signInListeners(){
        textViewSignInBtn.setOnClickListener {
            val email = editTextTextEmailAddress.text.toString()
            val password = editTextTextPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "fields are empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{task ->
                if(task.isSuccessful){
                    startActivity(Intent(this, AskInfoActivity::class.java))
                    Toast.makeText(this, "you logged successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }else {
                    Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }

        textViewDontHaveAcc.setOnClickListener {
            startActivity(Intent(this,SignUp::class.java))
        }
        textViewForgotPassword.setOnClickListener {
            startActivity(Intent(this,ForgotPassword::class.java))
        }
        editTextTextPassword.setOnClickListener{
            editTextTextPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }

        textViewDontHaveAcc.setOnClickListener {
            startActivity(Intent(this,SignUp::class.java))
        }

        textViewForgotPassword.setOnClickListener {
            startActivity(Intent(this,ForgotPassword::class.java))
        }
    }


    private fun init(){
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)
        editTextTextPassword = findViewById(R.id.editTextTextPassword)
        textViewSignInBtn = findViewById(R.id.textViewSignInBtn)
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword)
        textViewDontHaveAcc = findViewById(R.id.textViewDontHaveAcc)
    }
}