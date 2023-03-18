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

        //TODO liste des plats via API
        getDishesFromServer()

        val myDataset: Array<Dishe> = arrayOf( Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe(),Dishe() )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = DishesAdapter(this, myDataset)

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)


    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(tag,"$tag destroyed")
    }


    fun getDishesFromServer() {

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"

        // Request a string response from the provided URL.

        val body = JSONObject()
        body.put("id_shop","1")
        val stringRequest = JsonObjectRequest(
            Request.Method.POST, url, body,
            { response ->

                val data = Gson().fromJson(response.toString(), DataSource::class.java)
                println("The Winner is")
                println(data )
                println("END")
                //val dishes = data.daya[0].item.map{ it.categNameFr: ? ""}.tolist() as ArrayList
                //adapter.updateDishe(dishes)

            }, {
                    error ->
                Log.e(tag, "That didn't work!")
            }


        )

// Add the request to the RequestQueue.
        queue.add(stringRequest)
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



}