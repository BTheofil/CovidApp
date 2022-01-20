package hu.tb.covidapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.tb.covidapp.R
import hu.tb.covidapp.databinding.ActivityMainBinding
import hu.tb.covidapp.presentation.countryList.CountryListFragment
import hu.tb.covidapp.presentation.countyLooked.CountryLookedFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countryListFragment = CountryListFragment()
        val countryLookedFragment = CountryLookedFragment()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> setCurrentFragment(countryListFragment)
                R.id.looked -> setCurrentFragment(countryLookedFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.navHostFragment, fragment)
            commit()
        }
}