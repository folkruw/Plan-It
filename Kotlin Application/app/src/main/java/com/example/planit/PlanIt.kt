package com.example.planit

import android.app.Application
import android.content.Context

class PlanIt : Application() {
    // Allows for a single context throughout the application
    companion object {
        private lateinit var context: Context

        fun setContext(context : Context) {
            this.context = context
        }

        fun getContext(): Context{
            return this.context
        }
    }
}