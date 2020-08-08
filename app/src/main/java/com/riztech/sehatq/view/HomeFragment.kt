package com.riztech.sehatq.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.riztech.sehatq.databinding.FragmentHomeBinding
import com.riztech.sehatq.model.Product
import com.riztech.sehatq.viewModel.HomeViewModel
import com.riztech.sehatq.widget.DataAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class HomeFragment : Fragment(), DataAdapter.ProductClickListener {
    lateinit var view: FragmentHomeBinding

    private lateinit var dataAdapter: DataAdapter
    private val viewModel : HomeViewModel by viewModels()


    private var listDataObserver = Observer<ArrayList<Any>> {listDataObserver->
        listDataObserver?.let {
            dataAdapter.updateListData(listDataObserver)
        }
    }

    private var loadingObserver = Observer<Boolean> {isLoading ->
        if (isLoading) {
            view.shimmerFrameLayout.startShimmerAnimation()
            view.layoutView.visibility = View.GONE
            view.shimmerFrameLayout.visibility = View.VISIBLE
        }
        else {
            view.shimmerFrameLayout.stopShimmerAnimation()
            view.layoutView.visibility = View.VISIBLE
            view.shimmerFrameLayout.visibility = View.INVISIBLE
        }
    }

    private var errorObserver = Observer<Boolean> {isError ->
        if (isError){
            view.layoutError.visibility = View.VISIBLE
            view.rvHome.visibility = View.GONE
        }else{
            view.layoutError.visibility = View.GONE
            view.rvHome.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataAdapter = DataAdapter(context, arrayListOf(), this)

        viewModel.listData.observe(this.viewLifecycleOwner, listDataObserver)
        viewModel.loading.observe(this.viewLifecycleOwner, loadingObserver)
        viewModel.loadError.observe(this.viewLifecycleOwner, errorObserver)
        viewModel.refresh()

        this.view.rvHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dataAdapter
        }

        this.view.etSearch.keyListener = null

        this.view.etSearch.setOnClickListener {

            val action = HomeFragmentDirections.goToSearch()
            findNavController().navigate(action)
        }

        this.view.btRefresh.setOnClickListener {
            viewModel.refresh()
        }
        requireActivity().bottom_navigation.visibility = View.VISIBLE
    }

    override fun clickProduct(product: Product) {
        val action = HomeFragmentDirections.goToDetail(product)
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        view.shimmerFrameLayout.startShimmerAnimation()
    }

    override fun onPause() {
        view.shimmerFrameLayout.stopShimmerAnimation()
        super.onPause()
    }


}