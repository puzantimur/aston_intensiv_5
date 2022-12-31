package com.example.astonintensiv5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.astonintensiv5.listFragment.ContactsListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, ContactsListFragment())
                .commit()
        }
    }
}
