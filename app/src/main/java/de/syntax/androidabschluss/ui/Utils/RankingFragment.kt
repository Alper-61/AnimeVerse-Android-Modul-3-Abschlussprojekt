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


class RankingFragment : Fragment() {
    private lateinit var b : FragmentRankingBinding

    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentRankingBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        fragmentList.clear()
        fragmentTitleList.clear()

        fragmentList.add(TopAnimeFragment())
        fragmentList.add(TopCharactersFragment())

        val adapter = RankingViewPagerAdapter(fragmentList,requireActivity())
        b.viewPager2.adapter = adapter

        fragmentTitleList.add(getString(R.string.top_anime))
        fragmentTitleList.add(getString(R.string.top_characters))

        TabLayoutMediator(b.tabLayout2,b.viewPager2){tab,position->
            tab.setText(fragmentTitleList[position])
        }.attach()

        return b.root
    }


}