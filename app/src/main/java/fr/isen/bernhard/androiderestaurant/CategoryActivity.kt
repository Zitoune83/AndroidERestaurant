package fr.isen.bernhard.androiderestaurant

import adapter.DishesAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import model.Dishe
import fr.isen.bernhard.androiderestaurant.databinding.ActivityCategoryBinding
import model.DataSource
import org.json.JSONObject


class CategoryActivity: AppCompatActivity() {

    private val tag = "CategoryActivity"
    private lateinit var binding: ActivityCategoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Recuperation category
        val type = intent?.extras?.getString("category")

        getDishesFromServer( type.toString())

    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(tag,"$tag destroyed")
    }

    fun getDishesFromServer( type: String){

        var dishes: ArrayList<Dishe> = arrayListOf()

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"

        // Request a string response from the provided URL
        val body = JSONObject()
        body.put("id_shop","1")

        val stringRequest = JsonObjectRequest(
            Request.Method.POST, url, body,
            { response ->

                val data = Gson().fromJson(response.toString(), DataSource::class.java)

                dishes = fillListOfDishes(data,type)

                val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                recyclerView.adapter = DishesAdapter(this, dishes)

                // Use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                recyclerView.setHasFixedSize(true)
            }, {
                    error ->
                Log.e(tag, "That didn't work!")
            }
        )

// Add the request to the RequestQueue.
        queue.add(stringRequest)


       // println("THjjjjjjjjjE")
        //println(dishes.size)
       // println("END")

    }

    //Use for  parsing
    fun jsonToPrettyFormat(jsonString: String?): String? {
        val parser = JsonParser()
        val json = parser.parse(jsonString).asJsonObject
        val gson = GsonBuilder()
            .serializeNulls()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create()
        return gson.toJson(json)
    }

    //Titre/Photo/prix du plat
    fun fillListOfDishes(dataSource: DataSource, type:String ): ArrayList<Dishe>{

        val dishes: ArrayList<Dishe> = arrayListOf()

        when (type){
            "Entrées" -> for (currentDish in  dataSource.data[0].items) {
                val dish : Dishe = Dishe( currentDish)
                dishes.add(dish)
                println("ET")
                println(currentDish.prices)
                println("ANDD")
            }

            "Plats" -> for (currentDish in  dataSource.data[1].items) {
                val dish : Dishe = Dishe( currentDish)
                dishes.add(dish)
            }

            "Desserts" -> for (currentDish in  dataSource.data[2].items) {
                val dish : Dishe = Dishe( currentDish)
                dishes.add(dish)
            }

        }
        println("TEST HOLDER")
        println(dishes)
        println("THE EDNEEEE")
    return dishes
    }

}