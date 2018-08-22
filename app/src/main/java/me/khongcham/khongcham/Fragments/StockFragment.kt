package me.khongcham.khongcham.Fragments

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_stock.*
import me.khongcham.khongcham.Adapters.StockListAdapter
import me.khongcham.khongcham.Models.Stock
import me.khongcham.khongcham.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [StockFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [StockFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class StockFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_stock, container, false)
    }

    private val mockStockList = ArrayList<Stock>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = this.activity!!
        val adapter = StockListAdapter(activity, mockStockList)
        stock_rv.adapter = adapter
        stock_rv.setHasFixedSize(true)
        stock_rv.layoutManager = LinearLayoutManager(activity)
        button_deliver.setOnClickListener {
            //
        }
        button_cancel.setOnClickListener {
            //
        }
        addMockStock()
        syncItems(adapter)
    }

    private fun syncItems(adapter: StockListAdapter) {
        adapter.notifyDataSetChanged()
        when (adapter.stockList.isEmpty()) {
            true -> {
                stock_rv.visibility = View.GONE
                stock_no_items.visibility = View.VISIBLE
            }
            false -> {
                stock_rv.visibility = View.VISIBLE
                stock_no_items.visibility = View.GONE
            }
        }
    }

    private fun addMockStock() {
        var s = Stock(1, "Lays", null, 200.0, 20.0, 12, "ห่อ")
        if (!mockStockList.contains(s)) mockStockList.add(s)
        s = Stock(2, "Oishi", null, 250.0, 20.0, 5, "ขวด")
        if (!mockStockList.contains(s)) mockStockList.add(s)
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
                StockFragment().apply {
                    arguments = Bundle().apply {
                        //put params to Bundle
                    }
                }
    }
}
