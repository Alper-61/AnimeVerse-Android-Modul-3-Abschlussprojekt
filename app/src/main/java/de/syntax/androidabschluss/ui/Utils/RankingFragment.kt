package de.syntax.androidabschluss.ui.Utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.adapter.RankingViewPagerAdapter
import de.syntax.androidabschluss.databinding.FragmentRankingBinding
import de.syntax.androidabschluss.ui.TopAnimeFragment
import de.syntax.androidabschluss.ui.TopCharactersFragment

// Fragment, das ein TabLayout für Ranking-Ansichten enthält.
class RankingFragment : Fragment() {
    private lateinit var binding: FragmentRankingBinding // Bindung zur Layout-Datei für dieses Fragment.

    private val fragmentList = ArrayList<Fragment>() // Liste, die die Fragmente für die Tabs hält.
    private val fragmentTitleList = ArrayList<String>() // Liste, die die Titel für die Tabs hält.

    // Erstellt die Ansicht des Fragments und initialisiert das Binding.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRankingBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Konfiguriert das Verhalten und die Inhalte der Ansicht, nachdem diese erstellt wurde.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bereinigt die Listen, um sicherzustellen, dass keine alten Daten vorhanden sind.
        fragmentList.clear()
        fragmentTitleList.clear()

        // Fügt Fragmente zu der Liste hinzu.
        fragmentList.add(TopAnimeFragment())
        fragmentList.add(TopCharactersFragment())

        // Erstellt einen Adapter für ViewPager2 und setzt diesen.
        val adapter = RankingViewPagerAdapter(fragmentList, requireActivity())
        binding.viewPager2.adapter = adapter

        // Fügt Titel für die Tabs hinzu.
        fragmentTitleList.add(getString(R.string.top_anime))
        fragmentTitleList.add(getString(R.string.top_characters))

        // Verknüpft das TabLayout mit dem ViewPager2 und definiert, wie die Tabs beschriftet werden.
        TabLayoutMediator(binding.tabLayout2, binding.viewPager2) { tab, position ->
            tab.setText(fragmentTitleList[position])
        }.attach() // Stellt die Verbindung her und aktualisiert die Ansicht.
    }
}
