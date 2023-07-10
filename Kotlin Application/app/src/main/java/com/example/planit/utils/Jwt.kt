package com.example.planit.utils

import com.auth0.android.jwt.JWT

class Jwt {
    // To obtain the ID of the user from the token
    fun getID(token : String): Int {
        val jwt = JWT(token)
        val id = jwt.getClaim("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress").asString() //get custom claims
        return Integer.parseInt(id.toString())
    }

    // To obtain the ID company of the user from the token
    fun getIDCompany(token : String): Int {
        val jwt = JWT(token)
        val idCompany = jwt.getClaim("http://schemas.microsoft.com/ws/2008/06/identity/claims/userdata").asString() //get custom claims
        return Integer.parseInt(idCompany.toString())
    }

    // To obtain the ID company of the user from the token
    fun getFunction(token : String): String {
        val jwt = JWT(token)
        val function = jwt.getClaim("http://schemas.microsoft.com/ws/2008/06/identity/claims/role").asString() //get custom claims
        return function.toString()
    }
}