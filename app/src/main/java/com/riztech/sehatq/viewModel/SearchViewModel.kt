package com.riztech.sehatq.viewModel

import android.util.Patterns
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riztech.sehatq.model.Product

class SearchViewModel @ViewModelInject constructor() : ViewModel(){
    val listData by lazy { MutableLiveData<ArrayList<Product>>() }

    var emailValue = ""
        set(value) {
            field = value
            if (value.length >= 3){
               var productDummy = arrayListOf<Product>()

                for (i in 0..10){
                    val product = Product(i, "default", "Really Cool Watch $i", "$400", "This sets the stage for Casio’s newly updated G-Shock G-Lide watch collection (GBX100 family) whose beautiful new form includes both a new screen and a new operating system that has Bluetooth connectivity. It isn’t a full-on smartwatch, as it doesn’t need to be re-charged regularly, and it doesn’t have a lot of applications running on it, but it marries the best of what the G-Lide has to offer with modern connectivity and features that consumers expect.\\nThe module 3482 quartz movement inside the watch is new; the only downside is that there is no solar power-generation in this model, though it does contain a standard CR2032 cell battery that is said to have a two-year average life. The watch has both Bluetooth connectivity and a built-in step counter (pedometer), so this watch can actually function as an activity tracker when used in conjunction with the G-Shock Move smartphone app", 0)
                    productDummy.add(product)
                }

                listData.value = productDummy
            }
        }
}