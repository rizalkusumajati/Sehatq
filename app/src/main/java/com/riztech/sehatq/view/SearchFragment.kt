package com.riztech.sehatq.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.riztech.sehatq.R
import com.riztech.sehatq.databinding.FragmentSearchBinding
import com.riztech.sehatq.model.Product
import com.riztech.sehatq.viewModel.SearchViewModel
import com.riztech.sehatq.widget.DataAdapter
import com.riztech.sehatq.widget.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*


/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SearchFragment : Fragment(), ProductAdapter.ProductClickListener {
    lateinit var view: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var dataAdapter: ProductAdapter

    private var listDataObserver = Observer<ArrayList<Product>> {listDataObserver->
        if (listDataObserver == null){
            view.layoutInfoSearch.visibility = View.VISIBLE
            view.rvSearch.visibility = View.GONE
        }else{
            if (listDataObserver.size > 0){
                view.layoutInfoSearch.visibility = View.GONE
                view.rvSearch.visibility = View.VISIBLE
                dataAdapter.updateListData(listDataObserver)
            }else{
                view.layoutInfoSearch.visibility = View.VISIBLE
                view.rvSearch.visibility = View.GONE
            }
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
        view = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataAdapter = ProductAdapter(context, arrayListOf(), this)
        this.view.etSearchDetail.doOnTextChanged { text, _, _, _ ->  viewModel.emailValue = text.toString() }
        viewModel.listData.observe(this.viewLifecycleOwner, listDataObserver)
        this.view.rvSearch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dataAdapter
        }

        requireActivity().bottom_navigation.visibility = View.GONE
    }

    override fun clickProduct(product: Product) {
        val action = SearchFragmentDirections.goToDetail(product)
        findNavController().navigate(action)
    }

}