package com.example.reservapp.mainHost.ui.myplaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reservapp.databinding.FragmentMyplacesBinding
import com.example.reservapp.model.Place

class MyPlacesFragment : Fragment() {

    private lateinit var myplacesBinding: FragmentMyplacesBinding
    private lateinit var myPlacesViewModel: MyPlacesViewModel
    private lateinit var placesAdapter: PlacesAdapter
    private var placesList = mutableListOf<Place?>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        myPlacesViewModel = ViewModelProvider(this)[MyPlacesViewModel::class.java]
        myplacesBinding = FragmentMyplacesBinding.inflate(inflater, container, false)
        val view = myplacesBinding.root

        myPlacesViewModel.loadPlaces()

        myPlacesViewModel.errorMsg.observe(viewLifecycleOwner) {msg ->
            showErrorMsg(msg)
        }

        myPlacesViewModel.placesList.observe(viewLifecycleOwner) {placesList
            placesAdapter.appendItems(placesList)
        }

        placesAdapter = PlacesAdapter(placesList, onItemClicked = {onPlaceItemClicked(it)})

        myplacesBinding.placesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MyPlacesFragment.requireContext())
            adapter = placesAdapter
            setHasFixedSize(false)
        }

        myplacesBinding.newPlaceButton.setOnClickListener{
            findNavController().navigate(MyPlacesFragmentDirections.actionNavigationMyplacesToNewPlaceFragment())
        }
        return view
    }

    private fun onPlaceItemClicked(place: Place?) {

    }

    private fun showErrorMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

}