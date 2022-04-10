package com.example.caralert.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.caralert.BR
import com.example.caralert.R
import com.example.caralert.common.utils.hideKeyboard
import com.example.caralert.databinding.ActivityMainBinding
import com.example.caralert.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        val vm: MainViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)
    }

    private fun setupObservers() {
        binding.viewModel?.let {
            it.coupon.observe(this@MainActivity){ coupon ->
                binding.isActive = coupon != null && coupon.isActive
            }
            it.getSnackbarMsg().observe(this@MainActivity){ msg ->
                Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
            }
            it.isHideKeyboard().observe(this@MainActivity){ isHide ->
                if (isHide) hideKeyboard(this@MainActivity, binding.root)
            }
        }
    }
}