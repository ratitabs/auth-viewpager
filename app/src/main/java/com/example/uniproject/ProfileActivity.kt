package com.example.uniproject

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



class ProfileActivity: AppCompatActivity() {
    private lateinit var profileImageView: ImageView
    private lateinit var urlEditText: EditText
    private lateinit var textViewchangeavatar: TextView
    private lateinit var textViewChangePassword: TextView
    private lateinit var textViewlogout: TextView
    private lateinit var textViewnameLanstname: TextView
    private lateinit var textViewAge: TextView
    private lateinit var textViewNewyear: TextView
    private lateinit var textViewWorkEdu2: TextView

    private val firebaseAuth = Firebase.auth
    private val bd = FirebaseDatabase.getInstance().getReference("userInfo")
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        init()
        profileListeners()

        val rakeniaxalwels = intent.getStringExtra("axaliWlebi")
        val workEdu = intent.getStringExtra("workEdu")
        textViewNewyear.text = rakeniaxalwels
        textViewWorkEdu2.text = workEdu


        bd.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userInfo : User = snapshot.getValue(User::class.java) ?: return
                textViewnameLanstname.text = userInfo.username
                Glide.with(this@ProfileActivity).load(userInfo.url).into(profileImageView)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ProfileActivity, "cancelled", Toast.LENGTH_SHORT).show()
            }
        } )
    }

    private fun profileListeners(){
        textViewChangePassword.setOnClickListener {
            startActivity(Intent(this, ChangePassword::class.java))
        }
        textViewlogout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        textViewchangeavatar.setOnClickListener {
            val url = urlEditText.text.toString()
            val importedNameLastname = intent.getStringExtra("nameLastname")
            val age = intent.getIntExtra("age",0)
            val userInfo = User(importedNameLastname,url,age)
            bd.child(auth.currentUser?.uid!!).setValue(userInfo)
            Glide.with(this).load(url).into(profileImageView)
            textViewAge.text = age.toString()
            textViewnameLanstname.text = importedNameLastname
        }



    }

    private fun init(){
        profileImageView = findViewById(R.id.profileImageView)
        urlEditText = findViewById(R.id.urlEditText)
        textViewchangeavatar = findViewById(R.id.textViewchangeavatar)
        textViewChangePassword = findViewById(R.id.textViewChangePassword)
        textViewlogout = findViewById(R.id.textViewlogout)
        textViewnameLanstname = findViewById(R.id.textViewnameLanstname)
        textViewAge = findViewById(R.id.textViewAge)
        textViewNewyear = findViewById(R.id.textViewNewyear)
        textViewWorkEdu2 = findViewById(R.id.textViewWorkEdu2)
    }
}
