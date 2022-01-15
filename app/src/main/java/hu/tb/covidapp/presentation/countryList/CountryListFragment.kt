package hu.tb.covidapp.presentation.countryList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hu.tb.covidapp.R
import hu.tb.covidapp.databinding.FragmentCountryListBinding
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CountryListFragment : Fragment(R.layout.fragment_country_list) {

    private lateinit var countryListAdapter: CountryListAdapter

    private lateinit var binding: FragmentCountryListBinding
    private val viewModel: CountryListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCountryListBinding.bind(view)

        setupRecyclerView()
        subscribeToFlow()
    }

    private fun setupRecyclerView() = binding.rvCountries.apply {
        countryListAdapter = CountryListAdapter()
        adapter = countryListAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun subscribeToFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { list -> countryListAdapter.submitList(list.countries) }
        }
    }
}