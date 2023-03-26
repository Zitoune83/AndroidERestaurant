package fr.isen.bernhard.androiderestaurant.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import fr.isen.bernhard.androiderestaurant.DishFragment

private const val ARG_OBJECT = "object"
class PagerAdapter (fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 100

    override fun createFragment(position: Int): Fragment {

        // Return a NEW fragment instance in createFragment(int)
        val fragment = DishFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }

}
