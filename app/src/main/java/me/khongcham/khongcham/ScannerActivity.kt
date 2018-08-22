package me.khongcham.khongcham

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_scanner.*
import me.khongcham.khongcham.Fragments.HomeFragment.Companion.scanRequestCode

class ScannerActivity : AppCompatActivity(){

    private var goodsId: Int? = null
    private var goodsName: String? = null
    private var goodsQuantity = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        button_cancel.setOnClickListener {
            finish()
        }

        button_ok.setOnClickListener {
            if (goodsId != null) returnResult()
            else finish()
        }

        card_stock_quantity_add.setOnClickListener {
            goodsQuantity++
            syncQuantity()
        }

        card_stock_quantity_remove.setOnClickListener {
            goodsQuantity--
            syncQuantity()
        }

        syncQuantity()

    }

    private fun syncQuantity() {
        card_stock_quantity.text = goodsQuantity.toString()
    }

    fun onScanned() {
        if (goodsId == null) return
        //retrieve data from scanner
        //goodsId =
        //goodsName =
        //goodsLabel.text = goodsName
    }

    private fun returnResult() {
        val intent = Intent()
        //set intent data
        intent.putExtra(getString(R.string.scanner_goods_id), goodsId)
        intent.putExtra(getString(R.string.scanner_goods_quantity), goodsQuantity)
        setResult(scanRequestCode, intent)
        finish()
    }

}
