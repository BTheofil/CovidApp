package hu.tb.covidapp.presentation.countyLooked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hu.tb.covidapp.R
import hu.tb.covidapp.databinding.FragmentCountryLookedBinding

@AndroidEntryPoint
class CountryLookedFragment : Fragment(R.layout.fragment_country_looked) {

    private lateinit var countryLookedAdapter: CountryLookedAdapter

    private val viewModel: CountryLookedViewModel by viewModels()
    private lateinit var binding: FragmentCountryLookedBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.readAllData.observe(viewLifecycleOwner, { country ->
            countryLookedAdapter.submitList(country)
        })
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCountryLookedBinding.bind(view)

        setupRecyclerView()
    }

    private fun setupRecyclerView() = binding.rvLookedCountries.apply {
        countryLookedAdapter = CountryLookedAdapter()
        adapter = countryLookedAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

}