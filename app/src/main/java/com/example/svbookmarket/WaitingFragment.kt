package com.example.svbookmarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.svbookmarket.activities.adapter.OrderAdapter
import com.example.svbookmarket.activities.viewmodel.WaitingForDeliveryViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@AndroidEntryPoint
class WaitingFragment : Fragment() {

    private val viewModel: WaitingForDeliveryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
          val view: View = inflater.inflate(R.layout.fragment_waiting, container, false)
        val orderListAdapter: OrderAdapter = OrderAdapter(mutableListOf(), this.requireContext())
        viewModel.setWaitingOrder()
        val waitingOrderView: RecyclerView = view.findViewById(R.id.waitingOrder)
        waitingOrderView.apply {
            adapter = orderListAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
        getOrder(orderListAdapter)
        return view;
    }
    private fun getOrder(orderAdapter: OrderAdapter) {
        viewModel.waitingOrders.observe(this.viewLifecycleOwner, { changes ->
            orderAdapter.addOrder(changes)
        })
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConfirmedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WaitingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}