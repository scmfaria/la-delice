package br.com.ladelice.view;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.ladelice.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MenuActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testOpenMenuActivityFirstItem() throws Exception {
        Thread.sleep(500);
        onView(withId(R.id.appBar)).perform(ViewActions.swipeUp());

        Thread.sleep(1000);
        onView(withId(R.id.rvMenu)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        Thread.sleep(500);
        onView(withId(R.id.carouselView)).perform(ViewActions.swipeLeft());

        Thread.sleep(500);
        onView(withId(R.id.carouselView)).perform(ViewActions.swipeLeft());

        Thread.sleep(500);
        onView(withId(R.id.carouselView)).perform(ViewActions.swipeLeft());

        Thread.sleep(500);
        onView(withId(R.id.ivClose)).perform(click());

        Thread.sleep(1000);
    }

    @Test
    public void testOpenMenuActivitySecondItem() throws Exception{
        Thread.sleep(500);
        onView(withId(R.id.appBar)).perform(ViewActions.swipeUp());

        Thread.sleep(1000);
        onView(withId(R.id.rvMenu)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        Thread.sleep(500);
        onView(withId(R.id.carouselView)).perform(ViewActions.swipeLeft());

        Thread.sleep(500);
        onView(withId(R.id.carouselView)).perform(ViewActions.swipeLeft());

        Thread.sleep(500);
        onView(withId(R.id.ivClose)).perform(click());

        Thread.sleep(1000);
    }

    @Test
    public void testNavigationOnMenuActivityCompleted() throws Exception {
        Thread.sleep(500);
        onView(withId(R.id.appBar)).perform(ViewActions.swipeUp());

        Thread.sleep(1000);
        onView(withId(R.id.rvMenu)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        Thread.sleep(500);
        onView(withId(R.id.carouselView)).perform(ViewActions.swipeLeft());

        Thread.sleep(500);
        onView(withId(R.id.carouselView)).perform(ViewActions.swipeLeft());

        Thread.sleep(500);
        onView(withId(R.id.carouselView)).perform(ViewActions.swipeLeft());

        Thread.sleep(500);
        onView(withId(R.id.scrollView)).perform(ViewActions.swipeUp());

        Thread.sleep(1000);
        onView(withId(R.id.scrollView)).perform(ViewActions.swipeDown());

        Thread.sleep(1500);
        onView(withId(R.id.ivClose)).perform(click());

        Thread.sleep(1000);
        onView(ViewMatchers.withId(R.id.container)).perform(ViewActions.swipeDown());

        Thread.sleep(1000);
    }


}
