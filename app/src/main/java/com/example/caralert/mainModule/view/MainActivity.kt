package com.example.caralert.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.caralert.BR
import com.example.caralert.R
import com.example.caralert.common.entities.CouponEntity
import com.example.caralert.common.utils.hideKeyboard
import com.example.caralert.databinding.ActivityMainBinding
import com.example.caralert.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
   // private lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       /* binding = ActivityMainBinding.inflate((layoutInflater))
        setContentView(binding.root)*/
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupViewModel()
        SetupObservers()
      //  setupButtons()
    }

    private fun setupViewModel() {
//        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val vm: MainViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewmodel,vm)
    }

    private fun SetupObservers() {
        binding.viewmodel?.let {
            it.coupon.observe(this@MainActivity) { coupon ->
                binding.isActive = coupon != null && coupon.isActive
                /* if (coupon == null){
                binding.tilDescription.hint =   getString(R.string.descripcion)
                binding.tilDescription.isEnabled = true
                binding.btnCreate.visibility = View.VISIBLE
            }else{
                binding.etDescripciopn.setText(coupon.description)
                var status = getString(if (coupon.isActive) R.string.main_hint_active else R.string.descripcion)
                binding.tilDescription.hint = status
                binding.tilDescription.isEnabled = false
                binding.btnCreate.visibility = if(coupon.isActive) View.GONE else View.VISIBLE
            }*/

            }
            /*  mainViewModel.getResult().observe(this){coupon ->
            if (coupon == null){
                binding.tilDescription.hint =   getString(R.string.descripcion)
                binding.tilDescription.isEnabled = true
                binding.btnCreate.visibility = View.VISIBLE
            }else{
                binding.etDescripciopn.setText(coupon.description)
                var status = getString(if (coupon.isActive) R.string.main_hint_active else R.string.descripcion)
                binding.tilDescription.hint = status
                binding.tilDescription.isEnabled = false
                binding.btnCreate.visibility = if(coupon.isActive) View.GONE else View.VISIBLE
            }
        }*/
            it.getSnackbarMsg().observe(this) { msg ->
                Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
            }
            it.isHideKeyboard().observe(this@MainActivity) { isHide ->
                if (isHide) hideKeyboard(this, binding.root)
            }

        }
    }

   /* private fun setupButtons(){
        binding.btnConsult.setOnClickListener{
           // mainViewModel.consultCouponByCode(binding.etCoupon.text.toString())
            hideKeyboard(this,binding.root)
        }
        binding.btnCreate.setOnClickListener{
            val coupon = CouponEntity(
                code = binding.etCoupon.text.toString(),
                description = binding.etDescripciopn.text.toString())
           // mainViewModel.saveCoupon(coupon)
            hideKeyboard(this,binding.root)
        }
    }*/
}