package com.example.musicshop

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_order.*


class OrderActivity : AppCompatActivity() {

    companion object {
        const val USER_NAME = "userName"
        const val ITEM = "item"
        const val QUANTITY = "quantity"
        const val PRICE = "price"
    }

    private val subject = "Order From Music App"
    private val address = arrayOf("test@email.com")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val emailText = "User name ${intent.getStringExtra(USER_NAME)} \n" +
                "Item ${intent.getStringExtra(ITEM)} \n" +
                "Quantity ${intent.getIntExtra(QUANTITY, 0)} \n" +
                "Price ${intent.getIntExtra(PRICE, 0)}"
        tvOrderName.text = emailText

        btnSendOrder.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_EMAIL, address)
                putExtra(Intent.EXTRA_TEXT, emailText)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)

            } else {
                Toast.makeText(this, "Not email apps", Toast.LENGTH_LONG).show()
            }
        }
    }
}
