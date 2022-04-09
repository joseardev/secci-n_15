package com.example.caralert.mainModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caralert.R
import com.example.caralert.common.entities.CouponEntity
import com.example.caralert.common.utils.getMsgError
import com.example.caralert.mainModule.model.MainRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import java.nio.channels.MulticastChannel

class MainViewModel : ViewModel() {

private val repository = MainRepository()

    private val result = MutableLiveData(CouponEntity())
    fun getResult() = result

    val coupon = MutableLiveData<CouponEntity>()

    private val hideKeyboard = MutableLiveData<Boolean>()
    fun isHideKeyboard() = hideKeyboard


    private val snackbarMsg = MutableLiveData<Int>()
    fun  getSnackbarMsg() = snackbarMsg


    fun consultCouponByCodeOld(code: String){
        viewModelScope.launch {
            result.value = repository.consultCouponByCode(code)
        }
    }

    fun consultCouponByCode(){

        coupon.value?.code?.let { code->
            viewModelScope.launch {
                hideKeyboard.value = true
                result.value = repository.consultCouponByCode(code)
            }
        }
    }
   /* fun saveCouponOld(couponEntity: CouponEntity){
        viewModelScope.launch {
            try {
                repository.saveCoupo(couponEntity)
                consultCouponByCode(couponEntity.code)
                snackbarMsg.value = R.string.cupon_create
            }catch (e: Exception){
                snackbarMsg.value = getMsgError(e.message)
            }
        }

    }*/
    fun saveCoupon(){
        coupon.value?.let { couponEntity ->
            viewModelScope.launch {
                hideKeyboard.value = true
                try {
                    repository.saveCoupo(couponEntity)
                    consultCouponByCode()
                    snackbarMsg.value = R.string.cupon_create
                } catch (e: Exception) {
                    snackbarMsg.value = getMsgError(e.message)
                }
            }
        }

    }
}