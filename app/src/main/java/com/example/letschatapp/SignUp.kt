package com.example.letschatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText // lateinit - initialize after declaration
    private lateinit var edtPassword: EditText
    private lateinit var btnSignup: Button
    private lateinit var txtLogin: TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        edtName=findViewById(R.id.editText1)
        edtEmail=findViewById(R.id.editText2)
        edtPassword=findViewById(R.id.editText3)
        btnSignup=findViewById(R.id.btn_signup)
        txtLogin=findViewById(R.id.txt_login)
        mAuth = FirebaseAuth.getInstance() //initialize mAuth i.e firebase authentication


        btnSignup.setOnClickListener {
            val name = edtName.text.toString()  //getting name
            val email = edtEmail.text.toString()  //getting email
            val password = edtPassword.text.toString()  //getting password

            signUp(name,email,password)
        }

        //move to Login activity
        txtLogin.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            finish()
            startActivity(intent)
        }
    }

    //logic for creating user
    private fun signUp(name: String,email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                //code for jumping home

                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)

                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"Some error occured",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {

        mAuth= FirebaseAuth.getInstance()

        mDbRef= FirebaseDatabase.getInstance().getReference()  //initializing mDbRef
        mDbRef.child("user").child(uid).setValue(User(name,email,uid)) //created a node "user" in database
    }
}