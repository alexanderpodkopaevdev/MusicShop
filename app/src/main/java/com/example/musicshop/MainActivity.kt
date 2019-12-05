package com.example.musicshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRemove.setOnClickListener {
            if (tvQuantity.text.toString().toInt() > 0)
            tvQuantity.text = (tvQuantity.text.toString().toInt()).minus(1).toString()
        }
        btnAdd.setOnClickListener {
            tvQuantity.text = (tvQuantity.text.toString().toInt()).plus(1).toString()
        }
    }
}
