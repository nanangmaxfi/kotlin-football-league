package id.web.nanangmaxfi.footballeague.ui.detail_match

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.db.database
import id.web.nanangmaxfi.footballeague.model.Favorite
import id.web.nanangmaxfi.footballeague.model.MatchResponse
import id.web.nanangmaxfi.footballeague.repository.DetailMatchRepository
import id.web.nanangmaxfi.footballeague.utils.DateUtils
import id.web.nanangmaxfi.footballeague.utils.MatchUtils
import kotlinx.android.synthetic.main.activity_detail_match.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {
    override fun onDataError() {

    }

    private lateinit var presenter: DetailMatchPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var match: MatchResponse? = null
    private lateinit var matchType: String
    private lateinit var idMatch: String

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.elevation = 4.0F

        presenter = DetailMatchPresenter(this, DetailMatchRepository())
        idMatch = intent.getStringExtra(EXTRA_ID)
        val name = intent.getStringExtra(EXTRA_NAME)

        actionBar?.title = name

        favoriteState()
        presenter.getDetailMatch(idMatch)
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
        layout_scroll.visibility = View.GONE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
        layout_scroll.visibility = View.VISIBLE
    }

    override fun onDataLoaded(match: MatchResponse?) {
        this.match = match
        txt_date.text = DateUtils().dateFormat(match?.date+" "+match?.time)
        txt_time.text = DateUtils().timeFormat(match?.date+" "+match?.time)
        name_home.text = match?.homeTeam
        name_away.text = match?.awayTeam
        score_home.text = match?.homeScore
        score_away.text = match?.awayScore
        goal_home.text = match?.homeGoalDetails
        goal_away.text = match?.awayGoalDetails
        redcard_home.text = match?.homeRedCards
        redcard_away.text = match?.awayRedCards
        yellowcard_home.text = match?.homeYellowCards
        yellowcard_away.text = match?.awayYellowCards

        if (match?.homeScore.isNullOrBlank()){
            matchType = MatchUtils.NEXT_MACTH
        }
        else{
            matchType = MatchUtils.LAST_MATCH
        }

        presenter.getBadgeHome(match?.idHomeTeam)
        presenter.getBadgeAway(match?.idAwayTeam)
    }

    override fun showLogoHome(homeBadge: String) {
        Glide.with(applicationContext)
            .load(homeBadge)
            .into(logo_home)
    }

    override fun showLogoAway(awayBadge: String) {
        Glide.with(applicationContext)
            .load(awayBadge)
            .into(logo_away)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(ID_MATCH = {id})",
                    "id" to idMatch)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.ID_MATCH to match?.id,
                    Favorite.HOME_TEAM to match?.homeTeam,
                    Favorite.AWAY_TEAM to match?.awayTeam,
                    Favorite.DATE to match?.date,
                    Favorite.TIME to match?.time,
                    Favorite.HOME_SCORE to match?.homeScore,
                    Favorite.AWAY_SCORE to match?.awayScore,
                    Favorite.MATCH_TYPE to matchType
                )
            }
            layout_main.snackbar("Add to Favorite").show()
        }
        catch (e: SQLiteConstraintException){
            layout_main.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(ID_MATCH = {id})",
                    "id" to idMatch)
            }
            layout_main.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            layout_main.snackbar(e.localizedMessage).show()
        }
    }

}
