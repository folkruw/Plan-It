package com.example.planit.dtos

data class DtoInputEvent(val idEventEmployee:Int,
                            val idCompanies:Int,
                            val idAccoount : Int,
                            val startDate:String,
                            val endDate:String,
                            val isValid:Boolean,
                            val comments:String,
                            val types:String)
