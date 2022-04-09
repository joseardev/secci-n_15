package com.example.caralert.common.dataAccess

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.caralert.common.entities.CouponEntity

@Dao
interface CouponDao {
    @Query("SELECT * FROM CouponEntity Where code = :code")
    suspend fun consultCouponByCode(code:String): CouponEntity?

    @Insert
    suspend fun addCoupon(couponEntity: CouponEntity): Long

}