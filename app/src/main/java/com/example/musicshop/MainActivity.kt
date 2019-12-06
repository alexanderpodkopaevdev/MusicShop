package com.example.musicshop

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var spinnerArrayList: ArrayList<String>
    lateinit var spinnerAdapter: ArrayAdapter<String>
    val currency = "$"
    var price = 0
    val goodsMap = mutableMapOf("guitar" to 500, "drums" to 1000, "keyboard" to 100)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRemove.setOnClickListener {
            if (tvQuantity.text.toString().toInt() > 0)
                tvQuantity.text = (tvQuantity.text.toString().toInt()).minus(1).toString()
            updateSum()

        }
        btnAdd.setOnClickListener {
            tvQuantity.text = (tvQuantity.text.toString().toInt()).plus(1).toString()
            updateSum()

        }

        spinnerArrayList = arrayListOf("guitar", "drums", "keyboard")
        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spItem.adapter = spinnerAdapter

        spItem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateSum()
            }

        }

    }

    fun updateSum() {
        price =
            ((goodsMap[spItem.selectedItem] ?: 0) * (tvQuantity.text).toString().toInt())
        tvPrice.text = (getString(R.string.currency, price))
    }

}
