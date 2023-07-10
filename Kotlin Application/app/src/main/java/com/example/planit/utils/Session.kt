package com.example.planit.utils

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.planit.PlanIt
import com.example.planit.dtos.DtoInputAccount

class Session : AppCompatActivity() {
    val context = PlanIt.getContext()

    private fun sharedPreferences() : SharedPreferences {
        return context.getSharedPreferences("cookies", MODE_PRIVATE)
    }

    fun private(): String? {
        return sharedPreferences().getString("session", "")
    }

    private fun public(): String? {
        return sharedPreferences().getString("public", "")
    }

    fun info(): List<String> {
        val info : String = sharedPreferences().getString("info", "").toString()
        return info.split(";")
    }

    fun isLogged() : Boolean {
        return private() != ""
    }

    fun write(content:DtoInputAccount){
        val edit = sharedPreferences().edit()
        edit.putString("session", content.tokenPrivate)
        edit.putString("public", content.token)
        edit.putString("info", content.firstName + ";" + content.lastName)
        edit.apply()
    }

    fun delete(){
        val edit = sharedPreferences().edit()
        edit.clear()
        edit.apply()
    }

    fun getID(): Int {
        val jwt = Jwt()
        return jwt.getID(public().toString())
    }

    fun getIDCompany(): Int {
        val jwt = Jwt()
        return jwt.getIDCompany(public().toString())
    }

    fun getFunction(): String {
        val jwt = Jwt()
        return jwt.getFunction(public().toString())
    }
}