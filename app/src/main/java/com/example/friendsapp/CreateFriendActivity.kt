package com.example.friendsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_friend.*
import kotlinx.android.synthetic.main.activity_main.*
import model.Friends

class CreateFriendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_friend)

        btnBack.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        btnCreate.setOnClickListener{
            Friends.add(etName.text.toString(),etPhone.text.toString(),null)
            
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}