package id.web.nanangmaxfi.footballeague.ui.team_detail

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
import id.web.nanangmaxfi.footballeague.model.FavoriteTeam
import id.web.nanangmaxfi.footballeague.model.TeamResponse
import id.web.nanangmaxfi.footballeague.repository.TeamDetailRepository
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private lateinit var presenter: TeamDetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var idTeam: String
    private var team: TeamResponse? = null

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.elevation = 4.0F

        presenter = TeamDetailPresenter(this, TeamDetailRepository())
        val name = intent.getStringExtra(EXTRA_NAME)
        actionBar?.title = name

        idTeam = intent.getStringExtra(EXTRA_ID)
        favoriteState()
        presenter.getTeamDetail(idTeam)
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
        layout_detail.visibility = View.GONE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
        layout_detail.visibility = View.VISIBLE
    }

    override fun onDataLoaded(data: TeamResponse?) {
        this.team = data
        txt_title.text = data?.name
        txt_established.text = data?.established
        txt_stadium.text = data?.stadium
        txt_description.text = data?.description
        Glide.with(applicationContext)
            .load(data?.badge)
            .into(img_badge)
    }

    override fun onDataError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                .whereArgs("(ID_TEAM = {id})",
                    "id" to idTeam)
            val favorite = result.parseList(classParser<FavoriteTeam>())
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
                    FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.ID_TEAM to team?.id,
                    FavoriteTeam.NAME to team?.name,
                    FavoriteTeam.BADGE to team?.badge,
                    FavoriteTeam.STADIUM to team?.stadium
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
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(ID_TEAM = {id})",
                    "id" to idTeam)
            }
            layout_main.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            layout_main.snackbar(e.localizedMessage).show()
        }
    }

}
