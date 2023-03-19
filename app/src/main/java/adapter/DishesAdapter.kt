package adapter;

import android.content.Context
import android.view.LayoutInflater
import android.view.View;
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import model.Dishe
import fr.isen.bernhard.androiderestaurant.R

public class DishesAdapter(private val context: Context, private val dataDishes:ArrayList<Dishe>):RecyclerView.Adapter<DishesAdapter.DisheViewHolder>() {

        class DisheViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
                val textView: TextView = view.findViewById(R.id.textViewPrice)
                val textButton : Button = view.findViewById(R.id.button_select_dish)
                val imageView: ImageView = view.findViewById(R.id.imageView)

        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisheViewHolder {

                // create a new view
                val adapterLayout = LayoutInflater.from(parent.context)
                        .inflate(R.layout.dishe, parent, false)

                return DisheViewHolder(adapterLayout)
        }

        override fun getItemCount() = dataDishes.size


        //Action sur chaque vue
        override fun onBindViewHolder(holder: DisheViewHolder, position: Int) {


                //val dishes = dataDishes[position]
                //holder.textView.text = context.resources.getString(Dishe)
                //val dishe = dataDishes
                //holder.textView.text =  context.resources.getString(dishe.??)
                holder.textView.text =  dataDishes[position].disheItem.prices[0].price
                holder.textButton.text = dataDishes[position].disheItem.nameFr
                Picasso.get().load(dataDishes[position].disheItem.images[0]).fit().centerCrop().into(holder.imageView)

                println("THjjjjjjjjjE")
                println(dataDishes[position].disheItem.images[0])
                println("END")


        }
}









class CustomAdapter(private val dataSet: Array<String>) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

/**
 * Provide a reference to the type of views that you are using
 * (custom ViewHolder)
 */
class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
        // Define click listener for the ViewHolder's View
        textView = view.findViewById(R.id.textViewPrice)
        }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
        .inflate(R.layout.dishe, viewGroup, false)

        return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position]
        //viewHolder.textView.text = dataSet[0]
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.size

        }