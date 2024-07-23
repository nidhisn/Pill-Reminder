package com.example.pillreminder

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Reminder(var type:String, var time:String, var repeat:String,val soundEnabled: Boolean,
                    val vibrationEnabled: Boolean,
                    val reminderText: String):Parcelable
