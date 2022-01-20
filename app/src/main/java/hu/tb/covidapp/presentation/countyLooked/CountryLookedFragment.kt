package hu.tb.covidapp.presentation.countyLooked

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import hu.tb.covidapp.R
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CountryLookedFragment : Fragment(R.layout.fragment_country_looked) {

    private val viewModel: CountryLookedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDb()
    }

    private fun loadDb() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { list ->
                Log.d("DATA: ", list.countries.size.toString())
            }
        }
    }

}