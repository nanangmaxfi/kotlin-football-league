package id.web.nanangmaxfi.footballeague.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.ui.home.fragment.favorite.FavoriteFragment
import id.web.nanangmaxfi.footballeague.ui.home.fragment.league.LeagueFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private var content: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener {
            item ->
            when (item.itemId) {
                R.id.teams -> {
                    val fragment = LeagueFragment.newInstance()
                    addFragment(fragment)
                }
                R.id.favorites -> {
                    val fragment = FavoriteFragment()
                    addFragment(fragment)
                }
            }
            true
        }

        val fragment = LeagueFragment.newInstance()
        addFragment(fragment)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.main_container, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }
}
