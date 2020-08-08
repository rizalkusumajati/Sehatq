package com.riztech.sehatq.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.riztech.sehatq.R
import com.riztech.sehatq.databinding.FragmentHistoryBinding
import com.riztech.sehatq.model.Product
import com.riztech.sehatq.viewModel.HistoryViewModel
import com.riztech.sehatq.widget.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*


/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HistoryFragment : Fragment(), ProductAdapter.ProductClickListener {
    lateinit var view: FragmentHistoryBinding
    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var dataAdapter: ProductAdapter
    private var listDataObserver = Observer<ArrayList<Product>> { listDataObserver->
        if (listDataObserver == null){
            view.layoutInfo.visibility = View.VISIBLE
            view.rvHistory.visibility = View.GONE
        }else{
            if (listDataObserver.size > 0){
                view.layoutInfo.visibility = View.GONE
                view.rvHistory.visibility = View.VISIBLE
                dataAdapter.updateListData(listDataObserver)
            }else{
                view.layoutInfo.visibility = View.VISIBLE
                view.rvHistory.visibility = View.GONE
            }
        }
//        listDataObserver?.let {
//            dataAdapter.updateListData(listDataObserver)
//        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataAdapter = ProductAdapter(context, arrayListOf(), this)
        viewModel.listData.observe(this.viewLifecycleOwner, listDataObserver)
        viewModel.getUser()
        this.view.rvHistory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dataAdapter
        }

        requireActivity().bottom_navigation.visibility = View.VISIBLE
    }

    override fun clickProduct(product: Product) {
        val action = HistoryFragmentDirections.goToDetail(product)
        findNavController().navigate(action)
    }

}