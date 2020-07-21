package wsadurski.com.githubclient.presentation.repositories


import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import wsadurski.com.githubclient.R
import wsadurski.com.githubclient.RecyclerViewMatcher.Companion.withRecyclerView

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class RepositoriesActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(RepositoriesActivity::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(
            Intent(
                InstrumentationRegistry.getInstrumentation().targetContext,
                RepositoriesActivity::class.java
            )
        )
    }

    @Test
    fun repositoriesActivityTest() {
        //It's ugly but it works. IdlingResources should be used instead.
        Thread.sleep(3000)

        onView(withRecyclerView(R.id.repositories_recycler).atPosition(0))
            .check(matches(hasDescendant(withText("gpt-3"))));

        onView(withRecyclerView(R.id.repositories_recycler).atPosition(1))
            .check(matches(hasDescendant(withText("forem"))));
    }
}
