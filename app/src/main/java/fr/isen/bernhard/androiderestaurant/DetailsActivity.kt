package fr.isen.bernhard.androiderestaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import fr.isen.bernhard.androiderestaurant.adapter.ViewPagerAdapter
import fr.isen.bernhard.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.bernhard.androiderestaurant.model.Dishe
import fr.isen.bernhard.androiderestaurant.model.DisheDeserialized
import java.io.*
import fr.isen.bernhard.androiderestaurant.rss.*


class DetailsActivity(): FragmentActivity() {

    private val tag = "DetailsActivity"
    private lateinit var binding: ActivityDetailsBinding
    private var quantity = 0;
    private var totalPrice = 0.0
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Init binding
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        //Init pagerView
        var viewPager2: ViewPager2 = binding.pager
        var listPagerItem:ArrayList<ViewPagerItem> = ArrayList()

        textView = view.findViewById(R.id.number_article)

        updateShopping(textView)

        setContentView(view)

        //Recuperation de la données sérialisées
        val dishFlatten = intent?.extras?.getString("item")

        //Déserialise les données
        var dish: DisheDeserialized = DisheDeserialized()
        dish = deserializeDisheFlatten(dishFlatten.toString())

        //Generale la liste des items pour le pagerView
        listPagerItem = initListURL(dish.listURL)

        //Init et init pagerView
        var adapterPager: ViewPagerAdapter = ViewPagerAdapter(listPagerItem)
        viewPager2.adapter = adapterPager


        //Affichage Details Activity
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
            Toast.makeText(this, "faite chauffer la carte!!", Toast.LENGTH_SHORT).show()
            var line = generateLine(dish, quantity)
            writeToFile(line,this)
            updateShopping(textView)
        }

        val shoppingButton : View = findViewById(R.id.right_icon)
        shoppingButton.setOnClickListener{
            Toast.makeText(this, "shopping time!!", Toast.LENGTH_SHORT).show()

            Log.i(tag, "button shop clicked")
            val intent = Intent(this, ShoppingActivity::class.java)
            this.startActivity(intent)
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
            if(i.contains("images=")) {
                current.listURL.add(i.substringAfter("["))
            }
            else if(i.contains("http")) {
                current.listURL.add(i.substringBefore(']'))
            }
        }
        //println("TEST")
        //println(current.nameFr)
        //println(current.listIngredients)
        //println(current.prices)
        //println(current.listURL)

        return current

    }


    fun updateTotalPrice(price: Double){
        totalPrice = quantity*price
        binding.buttonTotal.text = totalPrice.toString() +" euros"
    }

    fun updateQuantity(){
        binding.quantity.text = quantity.toString()
    }


    fun initListURL(list:ArrayList<String>): ArrayList<ViewPagerItem>{

        var listItem: ArrayList<ViewPagerItem> = ArrayList()

        //println("DEB")

        for(i in list){

            var current: ViewPagerItem = ViewPagerItem()
            var string:String = i.substringAfter(" ")
            string = string.substringBefore(']')

            current.listURL = string
            listItem.add(current)
        }

        //println("FIN")
        return listItem

    }


    fun updateShopping(txt:TextView){
        val quantity: Int = readFromFile(this)
        txt.text = quantity.toString()
    }

    //NAME=xxx;PRICE=;QUANTITY=




}