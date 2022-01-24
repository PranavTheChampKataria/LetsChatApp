package com.example.letschatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

private lateinit var edtEmail:EditText // lateinit - initialize after declaration
private lateinit var edtPassword:EditText
private lateinit var btnLogin:Button
private lateinit var txtSignup:TextView
private lateinit var mAuth:FirebaseAuth
class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.`activity_log_in`)

        edtEmail=findViewById(R.id.editText1)
        edtPassword=findViewById(R.id.editText2)
        btnLogin=findViewById(R.id.btn_login)
        txtSignup=findViewById(R.id.txt_signup)

        mAuth = FirebaseAuth.getInstance() //initialize mAuth i.e firebase authentication

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()  //getting email
            val password = edtPassword.text.toString()  //getting password

            login(email,password)
        }

        //move to SignUp activity
        txtSignup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }

    //logic for logging user
    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for logging in user
                    val intent=Intent(this,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"User doesn't exist", Toast.LENGTH_SHORT).show()

                }
            }
    }
}