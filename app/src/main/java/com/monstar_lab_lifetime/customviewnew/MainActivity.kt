package com.monstar_lab_lifetime.customviewnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.view.animation.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setCustomView()

    }
    fun setCustomView(){
        val rotate=RotateAnimation(0.0f,180f,
            Animation.RELATIVE_TO_SELF.toInt(),0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        rotate.duration=3000
        rotate.interpolator=LinearInterpolator()
        rotate.repeatCount=Animation.INFINITE
        image_heart.startAnimation(rotate)
        image_ro.startAnimation(rotate)
    }

}