package fr.isen.bernhard.androiderestaurant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import fr.isen.bernhard.androiderestaurant.adapter.DishesAdapter
import fr.isen.bernhard.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.bernhard.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.bernhard.androiderestaurant.databinding.ActivityHomeBinding
import fr.isen.bernhard.androiderestaurant.model.Dishe
import fr.isen.bernhard.androiderestaurant.model.DisheDeserialized
import java.io.File.separator

class DetailsActivity: AppCompatActivity() {

    private val tag = "DetailsActivity"
    private lateinit var binding: ActivityDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var dishe: DisheDeserialized = DisheDeserialized()

        //Recuperation category
        val disheFlatten = intent?.extras?.getString("item")

        dishe = deserializeDisheFlatten(disheFlatten.toString())



    //Passer une liste d' URL pour le pagerView2

        //Affiche le titre
        binding.titleDishe.text = dishe.nameFr.toString()
        //Afficher liste des ingredient
        binding.ingredientsDishe.text = dishe.listIngredients.toString()
        //Bouton selecteur




    }

    fun deserializeDisheFlatten(string: String): DisheDeserialized{
        val current: DisheDeserialized = DisheDeserialized()
        var listString: ArrayList<String> = ArrayList()
        var ind =0;

        val list = string.split(",")

        for (i in list){

            if(i.contains("nameFr") && ind ==0) {
                current.nameFr = i.substringAfter("=")
                ind++
            }

            else if(i.contains("nameFr") && ind !=0) {
                current.listIngredients.add(i.substringAfter("="))
            }


            if(i.contains("price=")) {
                current.prices = i.substringAfter("=").toDouble()
            }
        }
        println("TEST")
        println(current.nameFr)
        println(current.listIngredients)
        println(current.prices)

        return current

    }






}