package com.example.valorantagentsguide.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "agents")
class AgentsEntity(
    @field:ColumnInfo(name = "uuid")
    @field:PrimaryKey
    val uuid: String,

    @field:ColumnInfo(name = "displayname")
    val displayName: String,

    @field:ColumnInfo(name = "description")
    val description: String,

    @field:ColumnInfo(name = "fullPortraitImg")
    val fullPortraitImg: String,

    @field:ColumnInfo(name = "displayIcon")
    val displayIcon: String,

    @field:ColumnInfo(name = "roleName")
    val roleName: String,

    @field:ColumnInfo(name = "roleIcon")
    val roleIcon: String,

    @field:ColumnInfo(name = "abilityName")
    val abilityName: String,

    @field:ColumnInfo(name = "abilityIcon")
    val abilityIcon: String,

    @field:ColumnInfo(name = "abilityDesc")
    val abilityDesc: String,

) : Parcelable