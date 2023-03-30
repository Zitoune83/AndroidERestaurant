package fr.isen.bernhard.androiderestaurant



import android.content.Intent
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import fr.isen.bernhard.androiderestaurant.databinding.ActivityHomeBinding
import fr.isen.bernhard.androiderestaurant.databinding.ActivityShoppingBinding
import fr.isen.bernhard.androiderestaurant.model.DisheDeserialized
import fr.isen.bernhard.androiderestaurant.rss.*
import java.io.*


class HomeActivity : AppCompatActivity() {

    private val tag = "HomeActivity"
    private lateinit var binding: ActivityHomeBinding
    //private lateinit var bindingToolbar: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
       //bindingToolbar = ActivityShoppingBinding.inflate((layoutInflater))
        //bindingToolbar.root
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

        val shoppingButton : View = findViewById(R.id.right_icon)
        shoppingButton.setOnClickListener{
            Toast.makeText(this, "shopping time!!", Toast.LENGTH_SHORT).show()

            Log.i(tag, "button shop clicked")
            val intent = Intent(this, ShoppingActivity::class.java)
            this.startActivity(intent)
        }

    }
     override fun onDestroy() {
         super.onDestroy()
         Log.d(tag,"$tag destroyed")
    }

    fun updateNumberArticle(){

    }
    override fun onResume() {
        super.onResume()
        val quantity:Int = readFromFile(this)
        var textView : TextView  = this.findViewById(R.id.number_article)
        textView.text = quantity.toString()
    }
}