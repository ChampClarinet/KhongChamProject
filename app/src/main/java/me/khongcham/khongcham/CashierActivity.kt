package me.khongcham.khongcham

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cashier.*
import me.khongcham.khongcham.Fragments.HomeFragment
import me.khongcham.khongcham.Utils.Utils

class CashierActivity : AppCompatActivity() {

    private var total: Double? = null
    private var get: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cashier)

        total = intent.getDoubleExtra(getString(R.string.total), -1.0)

        total_value.text = Utils.doubleToString(total!!)

        initializeGet()

        cal_ac.setOnClickListener { initializeGet() }
        cal0.setOnClickListener { keyDown("0") }
        cal1.setOnClickListener { keyDown("1") }
        cal2.setOnClickListener { keyDown("2") }
        cal3.setOnClickListener { keyDown("3") }
        cal4.setOnClickListener { keyDown("4") }
        cal5.setOnClickListener { keyDown("5") }
        cal6.setOnClickListener { keyDown("6") }
        cal7.setOnClickListener { keyDown("7") }
        cal8.setOnClickListener { keyDown("8") }
        cal9.setOnClickListener { keyDown("9") }
        cal_dot.setOnClickListener { keyDown(".") }
        cal20.setOnClickListener { add(20.0) }
        cal50.setOnClickListener { add(50.0) }
        cal100.setOnClickListener { add(100.0) }
        cal500.setOnClickListener { add(500.0) }
        cal1000.setOnClickListener { add(1000.0) }

        button_cash.setOnClickListener {
            returnResult(getString(R.string.status_ok))
        }

        button_void.setOnClickListener {
            returnResult(getString(R.string.status_void))
        }

        syncGet()

    }

    private fun returnResult(statusMsg: String) {
        val intent = Intent()
        intent.putExtra(getString(R.string.status), statusMsg)
        setResult(HomeFragment.cashierRequestCode, intent)
        finish()
    }

    private fun keyDown(s: String) {
        if (isInitialState()) {
            get = if (s == ".") "0."
            else s
        } else {
            get += s
        }
        syncGet()
    }

    private fun add(d: Double) {
        var prev = get.toDouble()
        prev += d
        get = Utils.doubleToString(prev)
        syncGet()
    }

    private fun isInitialState(): Boolean {
        return get == "0"
    }

    private fun initializeGet() {
        get = "0"
        syncGet()
    }

    private fun syncGet() {
        get_value.text = get
    }

}
