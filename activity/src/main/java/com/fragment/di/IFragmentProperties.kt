package com.fragment.di

import android.app.Fragment

import com.fragment.FragParam

/**
 * Created by clickapps on 1/11/17.
 */

interface IFragmentProperties<T> {

    fun animation(enter: Int, exit: Int, popEnter: Int, popExit: Int): T
}
