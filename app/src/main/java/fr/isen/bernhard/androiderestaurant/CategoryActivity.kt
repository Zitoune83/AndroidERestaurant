package fr.isen.bernhard.androiderestaurant

import Data.Dishe
import adapter.DishesAdapter
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import fr.isen.bernhard.androiderestaurant.databinding.ActivityCategoryBinding

class CategoryActivity: AppCompatActivity() {

    private val tag = "CategoryActivity"
    private lateinit var binding: ActivityCategoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




        val myDataset: Array<Dishe> = arrayOf( Dishe(),Dishe(),Dishe() )


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

}