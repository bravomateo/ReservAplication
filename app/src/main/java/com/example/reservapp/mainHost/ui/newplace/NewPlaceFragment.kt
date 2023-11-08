package com.example.reservapp.mainHost.ui.newplace

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.reservapp.databinding.FragmentNewPlaceBinding

class NewPlaceFragment : Fragment() {


    private lateinit var newPlaceViewModel: NewPlaceViewModel
    private lateinit var newPlaceBinding: FragmentNewPlaceBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        newPlaceBinding = FragmentNewPlaceBinding.inflate(inflater, container, false)
        newPlaceViewModel = ViewModelProvider(this)[NewPlaceViewModel::class.java]
        val view = newPlaceBinding.root

        newPlaceViewModel.errorMsg.observe(viewLifecycleOwner){msg->
            showErrorMsg(msg)
        }

        newPlaceViewModel.createPlaceSuccess.observe(viewLifecycleOwner) {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        with(newPlaceBinding) {
            saveButton.setOnClickListener {
                newPlaceViewModel.validateFields(
                    nameEditText.text.toString(),
                    cityEditText.text.toString(),
                    priceEditText.text.toString(),
                    capacityEditText.text.toString()
                )
            }
        }

        return view
    }


    private fun showErrorMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

}