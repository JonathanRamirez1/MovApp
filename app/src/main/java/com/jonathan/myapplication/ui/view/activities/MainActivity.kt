package com.jonathan.myapplication.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.jonathan.myapplication.R
import com.jonathan.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        animation()

        //TODO NO SE DEBERIA HACER ESTO
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }, 3800)
    }

    private fun animation() {
        binding.lottieAnimationViewSplash.animate().translationY(-1500F).setDuration(1000).startDelay = 2000
        binding.textViewMovApp.animate().translationYBy(1000F).setDuration(1000).startDelay = 2500

        val animationSlideUp: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        binding.textViewMovApp.animation = animationSlideUp

        val animationSlideDown: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        binding.linearLayoutSplash.animation = animationSlideDown
        binding.linearLayoutSplash.animate().translationY(1000F).setDuration(1000).startDelay = 2500
    }
}