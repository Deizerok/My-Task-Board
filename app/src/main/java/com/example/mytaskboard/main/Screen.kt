package com.example.mytaskboard.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {

    fun show(containerId: Int, supportFragmentManager: FragmentManager)

    object Empty : Screen {
        override fun show(containerId: Int, supportFragmentManager: FragmentManager) = Unit
    }

    abstract class Replace(private val clasz: Class<out Fragment>) : Screen {
        override fun show(containerId: Int, supportFragmentManager: FragmentManager) {
            for (i in 0 until supportFragmentManager.backStackEntryCount) {
                supportFragmentManager.popBackStack()
            }
            supportFragmentManager
                .beginTransaction()
                .replace(containerId, fragment())
                .addToBackStack(null)
                .commit()
        }

        protected open fun fragment(): Fragment = clasz.getDeclaredConstructor().newInstance()
    }

    abstract class Splash(private val clasz: Class<out Fragment>) : Screen {
        override fun show(containerId: Int, supportFragmentManager: FragmentManager) {
            for (i in 0 until supportFragmentManager.backStackEntryCount) {
                supportFragmentManager.popBackStack()
            }
            supportFragmentManager
                .beginTransaction()
                .replace(containerId, fragment())
                .commit()

        }

        protected open fun fragment(): Fragment = clasz.getDeclaredConstructor().newInstance()
    }

    abstract class Add(private val clasz: Class<out Fragment>) : Screen {
        override fun show(containerId: Int, supportFragmentManager: FragmentManager) {
            supportFragmentManager.beginTransaction()
                .add(containerId, fragment())
                .addToBackStack(clasz.simpleName)
                .commit()
        }

        protected open fun fragment(): Fragment = clasz.getDeclaredConstructor().newInstance()
    }

    object Pop : Screen {
        override fun show(containerId: Int, supportFragmentManager: FragmentManager) {
            supportFragmentManager.popBackStack()
        }
    }

}