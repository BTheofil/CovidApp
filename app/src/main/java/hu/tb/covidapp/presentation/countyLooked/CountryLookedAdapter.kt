package hu.tb.covidapp.presentation.countyLooked

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hu.tb.covidapp.data.local.entity.CountryEntity
import hu.tb.covidapp.databinding.CountryItemBinding

class CountryLookedAdapter() : RecyclerView.Adapter<CountryLookedAdapter.CountryListViewHolder>() {

    inner class CountryListViewHolder(var binding : CountryItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<CountryEntity>() {
        override fun areItemsTheSame(oldItem: CountryEntity, newItem: CountryEntity): Boolean {
            return oldItem.country == newItem.country
        }

        override fun areContentsTheSame(oldItem: CountryEntity, newItem: CountryEntity): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<CountryEntity>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder =
        CountryListViewHolder(CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        val country = differ.currentList[position]
        holder.binding.apply {
            countryTitle.text = country.country
            countryRecovered.text = country.recovered.toString()
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}