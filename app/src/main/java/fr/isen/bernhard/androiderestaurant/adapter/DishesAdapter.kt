package fr.isen.bernhard.androiderestaurant.adapter;

import android.graphics.Color.argb
import android.view.LayoutInflater
import android.view.View;
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.bernhard.androiderestaurant.model.Dishe
import fr.isen.bernhard.androiderestaurant.R


//Pas this et implemantation lambda
public class DishesAdapter(var dataDishes:ArrayList<Dishe>, var addressFunction: (Dishe) -> Unit): RecyclerView.Adapter<DishesAdapter.DisheViewHolder>() {

        class DisheViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
                val textView: TextView = view.findViewById(R.id.text_view_price)
                //val textButton : Button = view.findViewById(R.id.button_select_dish)
                val textView2 : TextView = view.findViewById(R.id.text_view_title)
                val imageView: ImageView = view.findViewById(R.id.image_view)
                //val viewBaground: View = view.findViewById(R.id.viewBackground)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisheViewHolder {

                // create a new view
                val adapterLayout = LayoutInflater.from(parent.context)
                        //.inflate(R.layout.dishe, parent, false)
                        .inflate(R.layout.recycler_view_dish, parent, false)

                return DisheViewHolder(adapterLayout)
        }

        override fun getItemCount() = dataDishes.size


        //Action sur chaque vue
        override fun onBindViewHolder(holder: DisheViewHolder, position: Int) {

                val color1 = argb(100,200,230,201)
                val color2 = argb(100,178,235,242)

                //val dishes = dataDishes[position]
                //holder.textView.text = context.resources.getString(Dishe)
                //val dishe = dataDishes
                //holder.textView.text =  context.resources.getString(dishe.??)
               // if (position%2 == 0)
                //holder.viewBaground.setBackgroundColor(color1)
                //else
                //        holder.viewBaground.setBackgroundColor(color2)


                var msg = dataDishes[position].disheItem.prices[0].price
                msg = msg+" €"

                holder.textView.text = msg
                holder.textView2.text = dataDishes[position].disheItem.nameFr

                holder.imageView.setOnClickListener {
                        addressFunction(dataDishes[position])
                }

                //Gestion des erreurs
                if(dataDishes[position].disheItem.images[0] != "") {

                       Picasso.get().load(dataDishes[position].disheItem.images[0]).fit().error(R.drawable.nopreviewavalaible)
                                .centerCrop().into(holder.imageView)

                }
                else{
                       Picasso.get().load(R.drawable.nopreviewavalaible).fit()
                                .centerCrop().into(holder.imageView )

                }

        }

        //Original
        //fun updateDishes(dishesFromAPI: ArrayList<String>) {
        //        dishes = dishesFromAPI  notifyDataSetChanged()
       // }

        fun updateDishes(dishesFromAPI: ArrayList<Dishe>) {
                dataDishes = dishesFromAPI
                notifyDataSetChanged ()
        }
}