package com.example.reservapp.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.reservapp.CorporateEventFragment
import com.example.reservapp.R
import com.example.reservapp.SocialEventFragment

private val TAB_TITLES = arrayOf(
    R.string.social_event,
    R.string.corporate_event
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when(position) {
            0-> SocialEventFragment()
            else -> CorporateEventFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}