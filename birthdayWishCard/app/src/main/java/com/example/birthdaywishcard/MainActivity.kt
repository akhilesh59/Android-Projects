package com.example.birthdaywishcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.ipname)

    }

    fun createBirthdayCard(view: View) {

        var name = "Error ho rhi hai"

        if(editText.text.toString().isNotEmpty()) {
            name = editText.text.toString()
        }

        val intent = Intent(this, birthdayCardActivity::class.java)
        intent.putExtra(birthdayCardActivity.NAME_EXTRA, name)
        startActivity(intent)

    }
}