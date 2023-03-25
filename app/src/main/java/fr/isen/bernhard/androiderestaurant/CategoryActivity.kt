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
import fr.isen.bernhard.androiderestaurant.model.Dishe
import fr.isen.bernhard.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.bernhard.androiderestaurant.model.DataSource
import fr.isen.bernhard.androiderestaurant.model.Items
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

        //Init
        var dishes: ArrayList<Dishe> = arrayListOf()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            //Reservation de la memoire necessaire à l' execution de la fonction "addFunction"
            //Réalisation de celle-ci, depend de la ou on l' a implementé: ici dans DishesAdapter
        recyclerView.adapter = DishesAdapter(dishes,::addFunction )

        var adapter: DishesAdapter = (recyclerView.adapter as DishesAdapter)
        adapter.updateDishes(dishes)
        getDishesFromServer( type.toString(), adapter )

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

    //Titre/Photo/prix du plat
    fun fillListOfDishes(dataSource: DataSource, type:String ): ArrayList<Dishe>{

        var dishes: ArrayList<Dishe> = arrayListOf()
//dishes = dataSource.data.firstOrNull{ it.nameFr == type}?.items?.map { Dishe(it) } as ArrayList
        when (type){
            "Entrées" -> for (currentDish in  dataSource.data[0].items) {
                val dish : Dishe = Dishe( currentDish)
                dishes.add(dish)
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

    return dishes
    }


    fun addFunction(dishe: Dishe){

        //Start activity & dishe arg
        Log.i(tag, "ImageView clicked")
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("item",dishe.disheItem.toString())
        this.startActivity(intent)

    }

}