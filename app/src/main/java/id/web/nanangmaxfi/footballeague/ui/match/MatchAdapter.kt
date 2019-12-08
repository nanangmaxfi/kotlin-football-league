package id.web.nanangmaxfi.footballeague.ui.match

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.web.nanangmaxfi.footballeague.model.ListMatchResponse
import id.web.nanangmaxfi.footballeague.ui.match.fragment.LastMatchFragment
import id.web.nanangmaxfi.footballeague.ui.match.fragment.NextMatchFragment

class MatchAdapter(fm: FragmentManager, idLeague: String) : FragmentPagerAdapter(fm) {

    //list objek Fragment
    private val pages = listOf(
            LastMatchFragment(idLeague),
            NextMatchFragment(idLeague)
    )

    //fragment yang akan dibuka
    override fun getItem(position: Int): Fragment {
        return pages[position] as Fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    //judul tabs
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Last Match"
            else -> "Next Match"
        }
    }
}