package com.riztech.sehatq.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.riztech.sehatq.R
import com.riztech.sehatq.util.openRoom
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Finding the Navigation Controller
        var navController = Navigation.findNavController(this, R.id.home_nav)

        // Setting Navigation Controller with the BottomNavigationView
        bottom_navigation.setupWithNavController(navController)


    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            openRoom(this)
        }
        return super.onKeyDown(keyCode, event)
    }
}