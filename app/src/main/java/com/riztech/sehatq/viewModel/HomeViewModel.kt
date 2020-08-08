package com.riztech.sehatq.viewModel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.riztech.sehatq.api.HomeApi
import com.riztech.sehatq.api.HomeApiService
import com.riztech.sehatq.database.ProductRepository
import com.riztech.sehatq.model.Category
import com.riztech.sehatq.model.HomeResponse
import com.riztech.sehatq.model.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel @ViewModelInject constructor (val homeApiService: HomeApiService) : ViewModel() {

    val listData by lazy { MutableLiveData<ArrayList<Any>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposable = CompositeDisposable()


    fun refresh(){
        loading.value = true
        getData()
    }

    private fun getData(){
        disposable.add(
            homeApiService.getHome()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith( object : DisposableSingleObserver<List<HomeResponse>>(){
                    override fun onSuccess(homeResponse: List<HomeResponse>) {
                        if (homeResponse != null && homeResponse.get(0) != null && homeResponse.get(0).data != null){
                            val data = homeResponse.get(0).data
                            loading.value = false
                            loadError.value = false

                            var listCategory : ArrayList<Category> = arrayListOf()

                            var listItem: ArrayList<Any> = arrayListOf()

                            data?.category?.let {
                                listCategory.addAll(it)
                            }
                            listItem.add(listCategory)
                            data?.productPromo?.let {
                                listItem.addAll(it)
                            }

                            listData.value = listItem


                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadError.value = true
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}