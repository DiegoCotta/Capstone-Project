package com.example.beerlovers.view.activities;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.example.beerlovers.R;
import com.example.beerlovers.model.Beer;
import com.example.beerlovers.model.Style;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class BeerDetailsActivityTest {
    private static final int ITEM_POSITION = 1;

    @Rule
    public ActivityTestRule<BeerDetailsActivity> mActivityTestRule = new ActivityTestRule<>(BeerDetailsActivity.class, false, false);


    @Before
    public void setUp() {
        Style style = new Style();
        style.setName("India Pale Ale");
        style.setShortName("IPA");
        Beer beer = new Beer();
        beer.setAbv("1");
        beer.setId("1");
        beer.setName("Vedette");
        beer.setNameDisplay("Vedette");
        beer.setIsOrganic("Y");
        beer.setIbu("2");
        beer.setIsRetired("N");
        beer.setStyle(style);
        Intent i = new Intent();
        i.putExtra(BeerDetailsActivity.BEER_KEY, beer);
        i.putExtra(BeerDetailsActivity.FROM, true);
        mActivityTestRule.launchActivity(i);
    }

    @Test
    public void screenTest() {
        onView(withId(R.id.tv_organic)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_retired)).check(matches(not(isDisplayed())));
        onView(withId(R.id.tv_style)).check(matches(withText(containsString("IPA"))));
        onView(withText("Vedette")).check(matches(isDisplayed()));
    }

    @Test
    public void favoriteClick() {
        onView(withId(R.id.favorite)).perform(click());
    }

    @Test
    public void tastedClick() {
        onView(withId(R.id.fab)).perform(click());
    }

    @Test
    public void backClick() {
        //https://stackoverflow.com/questions/23985181/click-home-icon-with-espresso/26898398
        onView(withContentDescription("Navigate up")).perform(click());
        onView(withText("Beer lovers")).check(matches(isDisplayed()));
    }

    public static Matcher<View> withBackgroundColor(final int backgroundColor) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                int color = ((ColorDrawable) view.getBackground().getCurrent()).getColor();
                return color == backgroundColor;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with background color value: " + backgroundColor);
            }
        };
    }
}