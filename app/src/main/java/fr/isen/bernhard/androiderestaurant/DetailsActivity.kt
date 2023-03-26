package fr.isen.bernhard.androiderestaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import fr.isen.bernhard.androiderestaurant.adapter.ViewPagerAdapter
import fr.isen.bernhard.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.bernhard.androiderestaurant.model.DisheDeserialized

class DetailsActivity(): FragmentActivity() {

    private val tag = "DetailsActivity"
    private lateinit var binding: ActivityDetailsBinding
    private var quantity = 0;
    private var totalPrice = 0.0
    lateinit var textView: TextView




        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

            var viewPager2: ViewPager2 = binding.pager
            var obtest:ArrayList<ViewPagerItem> = ArrayList()

            var current: ViewPagerItem= ViewPagerItem()

            obtest.add(current)
            obtest.add(current)
            obtest.add(current)
            obtest.add(current)

            var adapterPager: ViewPagerAdapter = ViewPagerAdapter(obtest)

            viewPager2.adapter = adapterPager


        var dish: DisheDeserialized = DisheDeserialized()

        //Recuperation category
        val dishFlatten = intent?.extras?.getString("item")

        dish = deserializeDisheFlatten(dishFlatten.toString())

    //Passer une liste d' URL pour le pagerView2


        binding.titleDishe.text = dish.nameFr.toString()
        binding.ingredientsDishe.text = dish.listIngredients.toString()
        binding.quantity.text = quantity.toString()

        binding.buttonLess.setOnClickListener {
            if (quantity>0){
                quantity--
                updateQuantity()
                updateTotalPrice(dish.prices.toDouble())
            }
        }

        binding.buttonMore.setOnClickListener {
            quantity++
            updateQuantity()
            updateTotalPrice(dish.prices.toDouble())
        }

        binding.buttonTotal.setOnClickListener {
            Toast.makeText(this, "faite chauffrer la carte!!", Toast.LENGTH_SHORT).show()
            textView = view.findViewById(R.id.number_article)
            textView.text = quantity.toString()






        }



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


    fun updateTotalPrice(price: Double){
        totalPrice = quantity*price
        binding.buttonTotal.text = totalPrice.toString() +" euros"
    }

    fun updateQuantity(){
        binding.quantity.text = quantity.toString()
    }

    companion object {
        private const val ARG_OBJECT = "object"
    }


}