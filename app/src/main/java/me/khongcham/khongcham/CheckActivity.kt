package me.khongcham.khongcham

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_check.*
import me.khongcham.khongcham.Adapters.BillAdapter
import me.khongcham.khongcham.Fragments.HomeFragment
import me.khongcham.khongcham.Models.Items
import me.khongcham.khongcham.Utils.Utils

class CheckActivity : AppCompatActivity() {

    private var total = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)

        val bill = intent.getSerializableExtra(getString(R.string.check_bill)) as HashMap<*, *>?
        val billList = ArrayList<Items>()
        if (bill != null && !bill.isEmpty()) {
            val keys = bill.keys
            keys.forEach { k ->
                billList.add(bill[k] as Items)
                total += (bill[k] as Items).getTotalPrice()
            }
        }
        val adapter = BillAdapter(this, billList)
        bill_rv.adapter = adapter
        bill_rv.setHasFixedSize(true)
        bill_rv.layoutManager = LinearLayoutManager(this)
        button_ok.setOnClickListener {
            // to cashier
            val intent = Intent(this, CashierActivity::class.java)
            intent.putExtra(getString(R.string.total), total)
            startActivityForResult(intent, HomeFragment.cashierRequestCode)
        }
        button_cancel.setOnClickListener {
            voided()
        }
        syncItems(adapter)
    }

    private fun voided() {
        val intent = Intent()
        intent.putExtra(getString(R.string.status), getString(R.string.status_void))
        setResult(HomeFragment.cashierRequestCode, intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val status = data?.getStringExtra(getString(R.string.status)) ?: finish()
        when (status) {
            getString(R.string.status_void) -> voided()
            getString(R.string.status_ok) -> {
                setResult(HomeFragment.cashierRequestCode, data)
                finish()
            }
        }
    }

    private fun syncItems(adapter: BillAdapter) {
        adapter.notifyDataSetChanged()
        when (adapter.bill.isEmpty()) {
            true -> {
                bill_rv.visibility = View.GONE
                bill_no_items.visibility = View.VISIBLE
            }
            false -> {
                bill_rv.visibility = View.VISIBLE
                bill_no_items.visibility = View.GONE
            }
        }
        balance_value.text = Utils.doubleToString(total)
    }
}
