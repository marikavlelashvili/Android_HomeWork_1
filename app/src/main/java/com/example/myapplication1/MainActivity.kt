package com.example.myapplication1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val emailText: EditText = findViewById(R.id.emailEditText)
        val recipientEmail : EditText = findViewById(R.id.recipientEmailEditText)
        val message : EditText = findViewById(R.id.messageEditText)
        val myButton: Button = findViewById(R.id.sendButton)

        myButton.setOnClickListener {
            val emailToSend = emailText.text.toString()
            val recipientToSend = recipientEmail.text.toString()
            val messageToSend = message.text.toString()


            val validationResult = validateInputs(emailToSend, recipientToSend, messageToSend)

            if (validationResult == null) {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("EmailValue", emailToSend)
                intent.putExtra("RecipientEmailValue", recipientToSend)
                intent.putExtra("MessageValue", messageToSend)
                Toast.makeText(this, "Button Clicked!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            } else {
                Toast.makeText(this, validationResult, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun validateInputs(email: String, recipient: String, message: String): String? {

        if (email.isEmpty() || recipient.isEmpty() || message.isEmpty()) {
            return "Email, recipient's email, and message must not be empty."
        }

        if (!email.contains("@") || !recipient.contains("@")) {
            return "Both emails must contain the '@' symbol."
        }

        if (message.length > 250) {
            return "Message cannot exceed 250 characters."
        }
        return null
    }
}