package com.example.astonintensiv5.extension

import androidx.fragment.app.Fragment
import com.example.astonintensiv5.MainActivity
import com.example.astonintensiv5.R

fun Fragment.addFragment(fragment: Fragment) {
    val activity = requireActivity() as? MainActivity ?: error(getString(R.string.smth_went_wrong))
    activity
        .supportFragmentManager
        .beginTransaction()
        .replace(R.id.container, fragment)
        .addToBackStack(null)
        .commit()
}
