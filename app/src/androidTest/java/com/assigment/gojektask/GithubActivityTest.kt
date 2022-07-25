package com.assigment.gojektask

import android.os.SystemClock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.assigment.gojektask.base.BaseUITest
import com.assigment.gojektask.di.generateTestAppComponent
import com.assigment.gojektask.helpers.recyclerItemAtPosition
import com.assigment.gojektask.repository.AppRepository
import com.assigment.gojektask.screen.GithubActivity
import com.assigment.gojektask.screen.RepositoryAdapter
import com.assigment.gojektask.utils.SharePreferenceHelper
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class GithubActivityTest : BaseUITest(){

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(GithubActivity::class.java, true, false)

    val sharePref : SharePreferenceHelper by inject()

    //Inject repository created with koin
    val appRepository : AppRepository by inject()
    //Inject Mockwebserver created with koin
    val  mMockWebServer : MockWebServer by inject()

    val nameTest = "octokit.rib"
    val descriptionTest = "Ruby toolkit for the Github API!"

    val scrollNameTest = "apps.js"
    val scrollDescriptionTest = "Github Apps toolset for Node.js"

    @Before
    fun start(){
        super.setUp()
        loadKoinModules(generateTestAppComponent(getMockWebServerUrl()).toMutableList())
    }

    @Test
    fun test_recyclerview_elements_for_expected_response() {
        mActivityTestRule.launchActivity(null)

        mockNetworkResponseWithFileContent("sample_api_response.json", HttpURLConnection.HTTP_OK)

        //Wait for MockWebServer to get back with response
        SystemClock.sleep(5000)

        //Check if item at 0th position is having 0th element in json
        onView(withId(R.id.repoRecyclerView))
            .check(
                ViewAssertions.matches(
                    recyclerItemAtPosition(
                        0,
                        ViewMatchers.hasDescendant(ViewMatchers.withText(nameTest))
                    )
                )
            )

        onView(withId(R.id.repoRecyclerView))
            .check(
                ViewAssertions.matches(
                    recyclerItemAtPosition(
                        0,
                        ViewMatchers.hasDescendant(ViewMatchers.withText(descriptionTest))
                    )
                )
            )

        //Scroll to last index in json
        onView(withId(R.id.repoRecyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RepositoryAdapter.DataViewHolder>(9))

        //Check if item at 9th position is having 9th element in json
        onView(withId(R.id.repoRecyclerView))
            .check(
                ViewAssertions.matches(
                    recyclerItemAtPosition(
                        9,
                        ViewMatchers.hasDescendant(ViewMatchers.withText(scrollNameTest))
                    )
                )
            )

        onView(withId(R.id.repoRecyclerView))
            .check(
                ViewAssertions.matches(
                    recyclerItemAtPosition(
                        9,
                        ViewMatchers.hasDescendant(ViewMatchers.withText(scrollDescriptionTest))
                    )
                )
            )

    }
}