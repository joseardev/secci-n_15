package com.example.caralert.mainModule.model

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import com.example.caralert.CouponsApplication
import com.example.caralert.common.dataAccess.CouponDao
import com.example.caralert.common.entities.CouponEntity
import com.example.caralert.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RoomDatabase {

    private val dao: CouponDao by lazy { CouponsApplication.database.couponDao() }

    suspend fun consultCouponByCode(code: String) = dao.consultCouponByCode(code)

    suspend fun saveCoupon(CouponEntity: CouponEntity) = withContext(Dispatchers.IO){

        try {
            dao.addCoupon(couponEntity = CouponEntity)

            }catch (e: Exception){
                (e as? SQLiteConstraintException).let { throw Exception(Constants.ERROR_EXIST) }
            throw Exception(e.message ?: Constants.ERROR_UNKNOW)
        }

    }
}