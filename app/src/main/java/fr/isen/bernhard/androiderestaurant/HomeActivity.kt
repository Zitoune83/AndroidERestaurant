package fr.isen.bernhard.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class HomeActivity : AppCompatActivity() {

    private val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

     override fun onDestroy() {
         super.onDestroy()
         Log.d(TAG,"HomeActivity destroyed")
    }
}