package br.com.ladelice.view;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.ladelice.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testSwipeAppBarLayout() throws Exception {
        Thread.sleep(1500);
        onView(ViewMatchers.withId(R.id.appBar)).perform(ViewActions.swipeUp());

        Thread.sleep(2000);
        onView(ViewMatchers.withId(R.id.container)).perform(ViewActions.swipeDown());

        Thread.sleep(1000);
    }

    @Test
    public void testRecyclerViewSwipe() throws Exception {
        Thread.sleep(1000);
        onView(ViewMatchers.withId(R.id.appBar)).perform(ViewActions.swipeUp());

        Thread.sleep(1000);
        onView(ViewMatchers.withId(R.id.rvMenu)).perform(ViewActions.swipeLeft());

        Thread.sleep(1000);
        onView(ViewMatchers.withId(R.id.rvMenu)).perform(ViewActions.swipeRight());

        Thread.sleep(1000);
        onView(ViewMatchers.withId(R.id.container)).perform(ViewActions.swipeDown());

        Thread.sleep(1000);
    }

    @Test
    public void testOpenNavigationView() throws Exception{
        Thread.sleep(1500);
        onView(withId(R.id.drawerLayout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        Thread.sleep(1000);
        onView(withId(R.id.drawerLayout))
                .perform(DrawerActions.close());

        Thread.sleep(1000);
    }
}
