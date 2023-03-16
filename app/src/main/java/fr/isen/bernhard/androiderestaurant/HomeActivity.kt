package fr.isen.bernhard.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class HomeActivity : AppCompatActivity() {

    private val tag = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Recupere l' @ de l' objet homeButton1
        //val button: Button = findViewById(R.id.homeButton1)



    }

     override fun onDestroy() {
         super.onDestroy()
         Log.d(tag,"HomeActivity destroyed")
    }
}