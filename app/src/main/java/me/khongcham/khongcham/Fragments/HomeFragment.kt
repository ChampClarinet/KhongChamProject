package me.khongcham.khongcham.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import me.khongcham.khongcham.AddNoBarCodeItemsActivity
import me.khongcham.khongcham.CheckActivity
import me.khongcham.khongcham.Models.Items
import me.khongcham.khongcham.R
import me.khongcham.khongcham.ScannerActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    //private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //get params from it: Bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private val currentBill = HashMap<Int, Items>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_scan.setOnClickListener {
            val intent = Intent(activity, ScannerActivity::class.java)
            startActivityForResult(intent, scanRequestCode)
        }
        button_no_barcode_items.setOnClickListener {
            val intent = Intent(activity, AddNoBarCodeItemsActivity::class.java)
            startActivityForResult(intent, scanRequestCode)
        }
        button_check.setOnClickListener {
            val intent = Intent(activity, CheckActivity::class.java)
            intent.putExtra(getString(R.string.check_bill), currentBill)
            startActivityForResult(intent, cashierRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            scanRequestCode -> {
                if (data != null) {
                    val goodsId = data.getIntExtra(getString(R.string.scanner_goods_id), -1)
                    val goodsQuantity = data.getIntExtra(getString(R.string.scanner_goods_quantity), -1)
                    if (goodsId > 0 && goodsQuantity > 0) mergeBill(getItems(goodsId, goodsQuantity))
                }
            }
            cashierRequestCode -> {
                val status = data?.getStringExtra(getString(R.string.status)) ?: return
                when(status){
                    getString(R.string.status_void) -> currentBill.clear()
                    getString(R.string.status_ok) -> {
                        var totalCash = 0.0
                        currentBill.forEach{
                            totalCash += it.value.getTotalPrice()
                        }
                        // use total cash
                        currentBill.clear()
                    }
                }
            }
        }
    }

    private fun getItems(goodsId: Int, goodsQuantity: Int): Items {
        //get goods initials from server and return
        val name = ""
        val pricePerUnit = 0.0
        return Items(goodsId, name, pricePerUnit, goodsQuantity)
    }

    private fun mergeBill(item: Items) {
        if (currentBill.isEmpty() || !currentBill.containsKey(item.id)) {
            currentBill[item.id] = item
        } else {
            currentBill[item.id]!!.quantity += item.quantity
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        //put params to Bundle
                    }
                }

        val scanRequestCode = 69
        val cashierRequestCode = 159

    }
}
