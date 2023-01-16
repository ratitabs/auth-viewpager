package com.example.uniproject

import android.support.v4.
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ChangePassword: AppCompatActivity() {
    private lateinit var editTextTextCurrnetPassword: EditText
    private lateinit var editTextTextNewPassword: EditText
    private lateinit var textViewnewPassword: TextView
    private lateinit var forgotPasswordGoBackButton: ImageView

    private var firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.changepassword_activity)

        changePasswordListeners()
        init()
    }


    private fun changePasswordListeners() {
        forgotPasswordGoBackButton.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }
        editTextTextNewPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

        textViewnewPassword.setOnClickListener {
            val currentPassword = editTextTextCurrnetPassword.text.toString()
            val newPassword = editTextTextNewPassword.text.toString()

            if(currentPassword == newPassword){
                Toast.makeText(this, "enter another password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if (newPassword.isEmpty()){
                Toast.makeText(this, "type some password min 6 letters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.currentUser?.updatePassword(newPassword)?.addOnCompleteListener(){ task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "password changed", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ProfileActivity::class.java))
                }else{
                    Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun init() {
        editTextTextCurrnetPassword = findViewById(R.id.editTextTextCurrnetPassword)
        editTextTextNewPassword = findViewById(R.id.editTextTextNewPassword)
        textViewnewPassword = findViewById(R.id.textViewnewPassword)
        forgotPasswordGoBackButton = findViewById(R.id.forgotPasswordGoBackButton)
    }
}