package com.riztech.sehatq.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.riztech.sehatq.R
import com.riztech.sehatq.databinding.FragmentDetailBinding
import com.riztech.sehatq.model.Product
import com.riztech.sehatq.util.getProgressDrawable
import com.riztech.sehatq.util.loadImage
import com.riztech.sehatq.view.DetailFragmentArgs.Companion.fromBundle
import com.riztech.sehatq.viewModel.DetailViewModel
import com.riztech.sehatq.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailFragment : Fragment() {
    lateinit var view: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    val itemArgument by lazy {
        fromBundle(requireArguments()).product
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        itemArgument?.let {
            this.view.titleDetail.text = it.title
            if (it.imageUrl.equals("default")){
                this.view.icProduct.loadImage(R.drawable.ic_product, getProgressDrawable(requireContext()))
            }else {
                this.view.icProduct.loadImage(it.imageUrl, getProgressDrawable(requireContext()))
            }
            it.loved?.let {
                if (it > 0){
                    this.view.likesDetail.text = "$it like(s)"
                }
            }
            this.view.priceDetail.text = it.price
            this.view.descriptionDetail.text = it.description
        }

        this.view.shopping.setOnClickListener {
            if (itemArgument != null){
                viewModel.insertProductsEntity(itemArgument!!)
            }else{
                viewModel.insertProductsEntity(Product())
            }

            val action = DetailFragmentDirections.goToHome()
            findNavController().navigate(action)

            Toast.makeText(requireContext(), "Success Checkout" , Toast.LENGTH_LONG).show()


        }

        this.view.share.setOnClickListener {
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"https://toko.sehatq.com")
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }

        this.view.love.setOnClickListener {
            this.view.love.setImageResource(R.drawable.ic_heart_clicked)
            this.view.likesDetail.text = "${itemArgument?.loved?.plus(1)} like(s)"
        }

        this.view.back.setOnClickListener {
            findNavController().navigateUp()
        }

        requireActivity().bottom_navigation.visibility = View.GONE
    }

}