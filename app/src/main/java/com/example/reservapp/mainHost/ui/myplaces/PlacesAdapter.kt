package com.example.reservapp.mainHost.ui.myplaces

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reservapp.R
import com.example.reservapp.databinding.CardViewPlaceItemBinding
import com.example.reservapp.model.Place

class PlacesAdapter (
    private val placesList: MutableList<Place?>,
    private val onItemClicked: (Place?) -> Unit,
    ) : RecyclerView.Adapter <PlacesAdapter.PlacesViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_place_item, parent, false)
        return PlacesViewHolder(view)
    }
    override fun getItemCount(): Int = placesList.size
    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        val place = placesList[position]
        holder.bind(place)
        holder.itemView.setOnClickListener{onItemClicked(place)}
    }

    fun appendItems(newList: MutableList<Place?>) {
        placesList.clear()
        placesList.addAll(newList)
        notifyDataSetChanged()
    }

    class PlacesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewPlaceItemBinding.bind(itemView)

        fun bind(place: Place?) {
            with(binding) {
                nameTextView.text = place?.name
                cityTextView.text = place?.city
                if(place?.urlPicture == null) {
                    pictureImageView.setImageResource(R.drawable.mas)
                }
            }
        }
    }



}