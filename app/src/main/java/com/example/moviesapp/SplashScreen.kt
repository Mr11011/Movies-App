package com.example.moviesapp


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    private val splashScreen = 4000
    private lateinit var topAnim: Animation
    private lateinit var bottomAnim: Animation
    private lateinit var imageView: ImageView
    private lateinit var label: TextView
    private lateinit var label2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hide status bar

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash_screen)


        //variables
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim)
        imageView = findViewById(R.id.splash_icon)
        label = findViewById(R.id.splash_label)
        label2 = findViewById(R.id.label2)

        //Animation for splash screen
        imageView.animation = topAnim
        label.animation = bottomAnim
        label2.animation = bottomAnim


        Handler().postDelayed({

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, splashScreen.toLong())


    }

}