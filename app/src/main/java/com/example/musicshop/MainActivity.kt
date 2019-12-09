package com.example.musicshop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerArrayList: ArrayList<String>
    private lateinit var spinnerAdapter: ArrayAdapter<String>

    private var price = 0
    private val goodsMap = mutableMapOf("guitar" to 500, "drums" to 1000, "keyboard" to 100)


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

        createSpinner()
        btnCard.setOnClickListener {
            val order = Order(
                etName.text.toString(),
                spItem.selectedItem.toString(),
                tvQuantity.text.toString().toInt(),
                price * tvQuantity.text.toString().toInt()
            )
            Log.d("Order", order.toString())
            val orderIntent = Intent(this,OrderActivity :: class.java).apply {
                putExtra(OrderActivity.USER_NAME,etName.text.toString())
                putExtra(OrderActivity.ITEM,spItem.selectedItem.toString())
                putExtra(OrderActivity.QUANTITY,tvQuantity.text.toString().toInt())
                putExtra(OrderActivity.PRICE,price * tvQuantity.text.toString().toInt())
            }
            startActivity(orderIntent)
        }
    }

    private fun createSpinner() {
        spinnerArrayList = ArrayList(goodsMap.keys)
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
                when (spItem.selectedItem) {
                    "guitar" -> ivGoods.setImageResource(R.drawable.gitar)
                    "drums" -> ivGoods.setImageResource(R.drawable.drums)
                    "keyboard" -> ivGoods.setImageResource(R.drawable.keyboard)
                    else -> return
                }
            }
        }
    }

    fun updateSum() {
        price = (goodsMap[spItem.selectedItem] ?: 0)
        val orderPrice = price * (tvQuantity.text).toString().toInt()
        tvPrice.text = (getString(R.string.currency, orderPrice))
    }

}
