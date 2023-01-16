package com.example.uniproject

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {

    private lateinit var editTextEmailAddress : EditText
    private lateinit var editTextTextPassword : EditText
    private lateinit var editTextConfirmTextPassword : EditText
    private lateinit var textViewsignUpBtn : TextView
    private lateinit var textViewdontHaveAcc : TextView

    private var firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)
        init()
        signupListeners()
    }



    private fun signupListeners(){

        editTextTextPassword.setOnClickListener{
            editTextTextPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }

        editTextConfirmTextPassword.setOnClickListener {
            editTextConfirmTextPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }

        textViewsignUpBtn.setOnClickListener{
            val email = editTextEmailAddress.text.toString()
            val password = editTextTextPassword.text.toString()
            val repeatPassword = editTextConfirmTextPassword.text.toString()

            if (password != repeatPassword){
                Toast.makeText(this, "password doesn't match the confirm password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (email.isEmpty() || password.isEmpty() || password.length < 6 || password.contains(' ')){
                Toast.makeText(this, "email or password field is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(){task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "congrats you're logged in ", Toast.LENGTH_SHORT).show()
                    goToLogin()
                }else {
                    Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
        textViewdontHaveAcc.setOnClickListener { goToLogin() }

    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun init() {
        editTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)
        editTextTextPassword = findViewById(R.id.editTextTextPassword)
        editTextConfirmTextPassword = findViewById(R.id.editTextconfirmTextPassword2)
        textViewsignUpBtn = findViewById(R.id.textViewsignUpBtn)
        textViewdontHaveAcc = findViewById(R.id.textViewdontHaveAcc)
    }
}
