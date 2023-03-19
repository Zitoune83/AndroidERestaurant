package fr.isen.bernhard.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.bernhard.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val tag = "HomeActivity"
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.homeButton1.setOnClickListener {
            Log.i(tag, "button1 clicked")
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", getString(R.string.home_button1))
            this.startActivity(intent)
        }

        binding.homeButton2.setOnClickListener {
            Log.i(tag, "button2 clicked")
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", getString(R.string.home_button2))
            this.startActivity(intent)
        }

        binding.homeButton3.setOnClickListener {
            Log.i(tag, "button3 clicked")
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", getString(R.string.home_button3))
            this.startActivity(intent)
        }





    }

     override fun onDestroy() {
         super.onDestroy()
         Log.d(tag,"$tag destroyed")
    }
}