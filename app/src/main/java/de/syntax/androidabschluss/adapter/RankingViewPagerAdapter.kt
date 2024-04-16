package de.syntax.androidabschluss.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// Adapterklasse für den ViewPager2, der Fragment-Listen verwaltet und anzeigt.
class RankingViewPagerAdapter (
    private val dataset: ArrayList<Fragment>, // Liste von Fragmenten, die angezeigt werden sollen.
    fragmentActivity: FragmentActivity // Kontext einer FragmentActivity, erforderlich für den Adapter.
) : FragmentStateAdapter(fragmentActivity) {

    // Gibt die Anzahl der Fragmente in der Liste zurück.
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Erstellt und liefert das Fragment für die angegebene Position.
    override fun createFragment(position: Int): Fragment {
        return dataset[position]
    }

}
