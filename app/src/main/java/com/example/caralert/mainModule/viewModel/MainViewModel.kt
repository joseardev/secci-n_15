package com.example.caralert.mainModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caralert.R
import com.example.caralert.common.entities.CouponEntity
import com.example.caralert.common.utils.getMsgErrorByCode
import com.example.caralert.mainModule.model.MainRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {
    private val repository = MainRepository()

    val coupon = MutableLiveData(CouponEntity())

    private val hideKeyboard = MutableLiveData<Boolean>()
    fun isHideKeyboard() = hideKeyboard

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMsg

    fun consultCouponByCode(){
        coupon.value?.code?.let { code ->
            viewModelScope.launch {
                hideKeyboard.value = true
                coupon.value = repository.consultCouponByCode(code) ?: CouponEntity(code = code, isActive = false)
            }
        }
    }

    fun saveCoupon(){
        coupon.value?.let { couponEntity ->
            viewModelScope.launch {
                hideKeyboard.value = true
                try {
                    couponEntity.isActive = true
                    repository.saveCoupon(couponEntity)
                    consultCouponByCode()
                    snackbarMsg.value = R.string.main_save_success
                } catch (e: Exception) {
                    snackbarMsg.value = getMsgErrorByCode(e.message)
                }
            }
        }
    }
}