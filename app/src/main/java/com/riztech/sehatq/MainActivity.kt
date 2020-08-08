package com.riztech.sehatq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.riztech.sehatq.util.openRoom
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            openRoom(this)
        }
        return super.onKeyDown(keyCode, event)
    }
}