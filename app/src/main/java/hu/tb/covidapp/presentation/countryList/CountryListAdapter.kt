package hu.tb.covidapp.presentation.countryList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hu.tb.covidapp.databinding.CountryItemBinding
import hu.tb.covidapp.domain.model.Country
import hu.tb.covidapp.domain.util.AddItemClickListener

class CountryListAdapter(private var addItemClickListener: AddItemClickListener) : RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder>() {

    inner class CountryListViewHolder(var binding : CountryItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.country == newItem.country
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Country>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder =
        CountryListViewHolder(CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        val country = differ.currentList[position]
        holder.binding.apply {
            countryTitle.text = country.country
            countryRecovered.text = country.recovered.toString()
            addDbBtn.setOnClickListener {
                addItemClickListener.addItemClick(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}