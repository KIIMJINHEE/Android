package com.example.memo

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Jinhee Kim
 * @email skesswswkk@naver.com
 * @created 2021/12/14
 * @desc
 * */

@Entity(tableName = "memo")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    var memo : String = ""
)