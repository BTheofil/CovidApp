package hu.tb.covidapp.presentation.countryList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hu.tb.covidapp.R
import hu.tb.covidapp.databinding.FragmentCountryListBinding
import hu.tb.covidapp.domain.model.Country
import hu.tb.covidapp.domain.util.AddItemClickListener
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CountryListFragment : Fragment(R.layout.fragment_country_list), AddItemClickListener {

    private lateinit var countryListAdapter: CountryListAdapter

    private lateinit var binding: FragmentCountryListBinding
    private val viewModel: CountryListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCountryListBinding.bind(view)

        setupRecyclerView()
        subscribeToFlow()
    }

    override fun addItemClick(country: Country) {
        viewModel.insertCountryToDb(country)
    }

    private fun setupRecyclerView() = binding.rvCountries.apply {
        countryListAdapter = CountryListAdapter(this@CountryListFragment)
        adapter = countryListAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun subscribeToFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest {
                when (it) {
                    is CountryListState.Success -> countryListAdapter.submitList(it.data)
                    is CountryListState.Error -> {
                        binding.ivNoConnection.visibility = View.VISIBLE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    else -> Unit
                }
            }
        }
    }
}