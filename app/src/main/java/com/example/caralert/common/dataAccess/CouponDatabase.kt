package com.example.caralert.common.dataAccess

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.caralert.common.dataAccess.CouponDao
import com.example.caralert.common.entities.CouponEntity

@Database(entities = [CouponEntity::class], version = 1)
abstract class CouponDatabase : RoomDatabase() {
    abstract fun couponDao(): CouponDao

}