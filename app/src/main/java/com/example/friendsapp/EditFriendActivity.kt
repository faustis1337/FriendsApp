package com.example.friendsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_friend.*
import model.Friends


class EditFriendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_friend)

        val contactId = intent.getIntExtra("contact_id",-1)
        val contactImage = intent.getStringExtra("contact_image")

        if(contactId == -1){
            this.finish()
        }
        etName.setText(intent.getStringExtra("contact_name"))
        etPhone.setText(intent.getStringExtra("contact_phone"))

        btnBack.setOnClickListener{
            this.finish()
        }
        btnSave.setOnClickListener{
            Friends.update(contactId,etName.text.toString(),etPhone.text.toString())
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}