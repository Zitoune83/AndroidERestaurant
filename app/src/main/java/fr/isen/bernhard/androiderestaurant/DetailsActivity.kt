package fr.isen.bernhard.androiderestaurant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import fr.isen.bernhard.androiderestaurant.adapter.DishesAdapter
import fr.isen.bernhard.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.bernhard.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.bernhard.androiderestaurant.databinding.ActivityHomeBinding
import fr.isen.bernhard.androiderestaurant.model.Dishe

class DetailsActivity: AppCompatActivity() {

    private val tag = "DetailsActivity"
    private lateinit var binding: ActivityDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //Recuperation category
        val dishe = intent?.extras?.getString("item")

        println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")
        println(dishe)


    }






}