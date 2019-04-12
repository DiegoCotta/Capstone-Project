package com.example.beerlovers.view.activities;


import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.beerlovers.R;
import com.example.beerlovers.dao.AppDatabase;
import com.example.beerlovers.service.RequestService;
import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private static final int ITEM_POSITION = 1;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    IdlingResource resource = OkHttp3IdlingResource.create("OkHttp", RequestService.okHttp);


    @Before
    public void setUp() {

        IdlingRegistry.getInstance().register(resource);
    }

    @After
    public void tearDown() throws Exception {
        IdlingRegistry.getInstance().unregister(resource);
    }

    @Test
    public void itemClickTest() {

        // https://github.com/googlesamples/android-testing/blob/master/ui/espresso/RecyclerViewSample/app/src/androidTest/java/com/example/android/testing/espresso/RecyclerViewSample/RecyclerViewSampleTest.java
        onView(withId((R.id.rvList)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_POSITION, click()));

        //https://stackoverflow.com/questions/36329978/how-to-check-toolbar-title-in-android-instrumental-test
        onView(withText("Beer lovers")).check(doesNotExist());

    }

    @Test
    public void searchTest() {
        onView(withId(R.id.search)).perform(click());
        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("a"));
        onView(withId(android.support.design.R.id.search_src_text))
                .perform(pressImeActionButton());
        RecyclerView recyclerView = mActivityTestRule.getActivity().findViewById(R.id.rvList);
        assertThat(recyclerView.getAdapter().getItemCount(), greaterThan(0));
    }

    @Test
    public void favoriteClick() {
        onView(withId(R.id.navigation_favorite)).perform(click());
        onView(withId(R.id.search)).check(doesNotExist());
        RecyclerView recyclerView = mActivityTestRule.getActivity().findViewById(R.id.rvList);
        assertThat(recyclerView.getAdapter().getItemCount(), equalTo(0));
    }

    @Test
    public void tastedClick() {
        onView(withId(R.id.navigation_tasted)).perform(click());
        onView(withId(R.id.search)).check(doesNotExist());
        RecyclerView recyclerView = mActivityTestRule.getActivity().findViewById(R.id.rvList);
        assertThat(recyclerView.getAdapter().getItemCount(), equalTo(0));
    }
}