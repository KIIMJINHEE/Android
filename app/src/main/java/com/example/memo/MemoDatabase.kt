package com.example.memo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

/**
 * @author Jinhee Kim
 * @email skesswswkk@naver.com
 * @created 2021/12/14
 * @desc
 * */

@Database(entities = arrayOf(MemoEntity::class), version = 1)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDAO() : MemoDAO

    companion object{
        var INSTANCE : MemoDatabase? = null

        fun getInstance(context : Context) : MemoDatabase? {
            if(INSTANCE == null){
                synchronized(MemoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    MemoDatabase::class.java, "memo.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}