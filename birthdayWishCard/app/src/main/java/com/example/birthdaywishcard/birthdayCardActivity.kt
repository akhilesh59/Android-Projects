package com.example.birthdaywishcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class birthdayCardActivity : AppCompatActivity() {

    companion object {
        const val NAME_EXTRA = "name_extra"
    }

    lateinit var card : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday_card)

        card = findViewById(R.id.greetText)

        val name = intent.getStringExtra(NAME_EXTRA)
        card.text = "Happy Birthday \n $name!"
    }
}