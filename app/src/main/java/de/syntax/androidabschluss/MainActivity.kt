package de.syntax.androidabschluss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import de.syntax.androidabschluss.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ermittelt das NavHostFragment, das für die Navigation zuständig ist.
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        // Verknüpft die untere Navigationsleiste mit dem NavController.
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navHost.navController)

        // Listener für die Elemente der unteren Navigationsleiste.
        binding.bottomNavigationView.setOnItemSelectedListener{
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