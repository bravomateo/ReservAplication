package com.example.reservapp.ui.main.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.reservapp.MyPlacesFragment
import com.example.reservapp.MyProfileHostFragment
import com.example.reservapp.R



private val TAB_TITLES = arrayOf(
    R.string.plalces_text,
    R.string.profile_host
)


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when(position) {
            0-> MyPlacesFragment()
            else -> MyProfileHostFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}