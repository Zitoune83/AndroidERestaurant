package fr.isen.bernhard.androiderestaurant.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.bernhard.androiderestaurant.R
import fr.isen.bernhard.androiderestaurant.ViewPagerItem

class ViewPagerAdapter (private var listItem: ArrayList<ViewPagerItem> = ArrayList()): RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    class ViewPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var textView: TextView = itemView.findViewById(R.id.title_dish)
        var imageView: ImageView = itemView.findViewById((R.id.image_dish))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        var view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewpager_item, parent,false)

        return ViewPagerHolder(view)
    }

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {

        var viewPagerItem: ViewPagerItem = listItem.get(position)


        if (listItem[position].listURL != "") {

            Picasso.get().load(listItem[position].listURL).error(R.drawable.nopreviewavalaible)
                .into(holder.imageView)

        } else {
            Picasso.get().load(R.drawable.nopreviewavalaible)
                .into(holder.imageView)
        }
    }

}