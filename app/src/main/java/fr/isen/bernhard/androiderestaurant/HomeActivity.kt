package fr.isen.bernhard.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import fr.isen.bernhard.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val tag = "HomeActivity"
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Recupere l' @ de l' objet homeButton1
        //val button: Button = findViewById(R.id.homeButton1)
        //binding.homeButton1.setOnClickListener {
         //   println("ROOOOOOOOOOOL")
       // }


    }

     override fun onDestroy() {
         super.onDestroy()
         Log.d(tag,"HomeActivity destroyed")
    }
}