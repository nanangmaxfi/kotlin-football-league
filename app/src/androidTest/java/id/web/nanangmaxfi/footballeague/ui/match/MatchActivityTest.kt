package id.web.nanangmaxfi.footballeague.ui.match

import android.content.Intent
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import id.web.nanangmaxfi.footballeague.R
import id.web.nanangmaxfi.footballeague.model.LeagueResponse
import id.web.nanangmaxfi.footballeague.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_match.*
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatchActivityTest {
    @Rule
    @JvmField
    var activityRule = object : ActivityTestRule<MatchActivity>(MatchActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return Intent(InstrumentationRegistry.getInstrumentation().targetContext, MatchActivity::class.java)
                .apply {
                    val league = LeagueResponse("4328","English Premier League","1992","England","Premier League","The Premier League","https://www.thesportsdb.com/images/media/league/poster/v8d7nf1535455996.jpg","","https://www.thesportsdb.com/images/media/league/badge/i6o0kh1549879062.png")
                    putExtra(MatchActivity.EXTRA_LEAGUE, league)
                }
        }
    }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun testSearch() {
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Liverpool"))

        onView(withId(R.id.rv_search_match)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_search_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        onView(withId(R.id.rv_search_match)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

    }
}