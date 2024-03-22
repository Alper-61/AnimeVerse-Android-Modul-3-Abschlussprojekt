package de.syntax.androidabschluss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import de.syntax.androidabschluss.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(b.bottomNavigationView,navHost.navController)

        b.bottomNavigationView.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.homeFragment -> {
                    findNavController(R.id.fragmentContainerView).navigate(R.id.homeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.favoriteFragment -> {
                    findNavController(R.id.fragmentContainerView).navigate(R.id.favoriteFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.rankingFragment -> {
                    findNavController(R.id.fragmentContainerView).navigate(R.id.rankingFragment)
                    return@setOnItemSelectedListener true
                }


            }
            false
        }

    }
}