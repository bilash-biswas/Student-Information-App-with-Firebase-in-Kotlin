package com.example.firebaserealtimedatabase

import android.content.Context
import android.widget.Toast


class Utility {
    companion object{
        fun toast(context: Context, text: String){
            return Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }
}