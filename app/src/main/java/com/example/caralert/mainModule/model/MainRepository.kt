package com.example.caralert.mainModule.model

import com.example.caralert.common.entities.CouponEntity
import com.example.caralert.common.utils.Constants
import com.example.caralert.common.utils.validateTextCode
import java.lang.Exception

class MainRepository {

    private val roomDatabase = RoomDatabase()

    suspend fun consultCouponByCode(code:String) = roomDatabase.consultCouponByCode(code)

    suspend fun saveCoupon(couponEntity: CouponEntity){
        if(validateTextCode(couponEntity.code)){
            roomDatabase.saveCoupon(couponEntity)
        }else{
            throw Exception(Constants.ERROR_LENGTH)
        }
    }
}