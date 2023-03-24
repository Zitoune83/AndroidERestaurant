package fr.isen.bernhard.androiderestaurant

import android.content.Intent
import fr.isen.bernhard.androiderestaurant.adapter.DishesAdapter
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

//
        //
        var dishes: ArrayList<Dishe> = arrayListOf()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = DishesAdapter(this, dishes)

        var adapter: DishesAdapter = (recyclerView.adapter as DishesAdapter)
        adapter.updateDishes(dishes)
        getDishesFromServer( type.toString(), adapter )

       //binding.recyclerView. .apply {

         //   Log.i(tag as String?, "recycledView clicked")
            //val intent = Intent(this, DetailsActivity::class.java)
            //intent.putExtra("category", getString(R.string.home_button3))
            //this.startActivity(intent)
           // println("Recylcle    !!!")
            //println(dishes.size)
            //println("END")



       // }


    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(tag,"$tag destroyed")
    }

    fun getDishesFromServer( type: String, adapter: DishesAdapter){

        //var dishes: ArrayList<Dishe> = arrayListOf()
        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        //recyclerView.adapter = DishesAdapter(this, dishes)

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

                adapter.dataDishes = fillListOfDishes(data,type)
                //dishes = dataSource.data.firstOrNull{ it.nameFr == type}?.items?.map { Dishe(it) } as ArrayList

                adapter.updateDishes( adapter.dataDishes )
                //(recyclerView.adapter as DishesAdapter).updateDishes(dishes)

            }, {
                    erreur ->
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
    //fun jsonToPrettyFormat(jsonString: String?): String? {
        //val parser = JsonParser()
       // val json = parser.parse(jsonString).asJsonObject
      //  val gson = GsonBuilder()
        //    .serializeNulls()
       //     .disableHtmlEscaping()
       //     .setPrettyPrinting()
       //     .create()
     //   return gson.toJson(json)
 //   }

    //Titre/Photo/prix du plat
    fun fillListOfDishes(dataSource: DataSource, type:String ): ArrayList<Dishe>{

        var dishes: ArrayList<Dishe> = arrayListOf()
//dishes = dataSource.data.firstOrNull{ it.nameFr == type}?.items?.map { Dishe(it) } as ArrayList
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