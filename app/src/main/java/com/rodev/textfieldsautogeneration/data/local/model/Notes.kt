package com.rodev.textfieldsautogeneration.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "notes"
)
data class Notes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "viewId")
    var viewId: Int = -1,
    @ColumnInfo(name = "info")
    var info: String? = null
) : Serializable