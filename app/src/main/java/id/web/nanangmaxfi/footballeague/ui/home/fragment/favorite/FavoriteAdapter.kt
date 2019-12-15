package id.web.nanangmaxfi.footballeague.ui.home.fragment.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.web.nanangmaxfi.footballeague.ui.home.fragment.favorite.tab.LastFavoriteFragment
import id.web.nanangmaxfi.footballeague.ui.home.fragment.favorite.tab.NextFavoriteFragment

class FavoriteAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
    //list objek Fragment
    private val pages = listOf(
        LastFavoriteFragment(),
        NextFavoriteFragment()
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