package com.example.p4_lamzone_mareu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;

import static com.example.p4_lamzone_mareu.utils.RecyclerViewItemCountAssertion.withItemCount;

import android.content.Context;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import static org.hamcrest.core.IsNull.notNullValue;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

import com.example.p4_lamzone_mareu.di.DI;
import com.example.p4_lamzone_mareu.service.MeetingApiService;
import com.example.p4_lamzone_mareu.ui.list.MeetingListActivity;
import com.example.p4_lamzone_mareu.utils.DeleteViewAction;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InstrumentedTest {

    private static int ITEMS_COUNT = 3;
    private static String NEW_MEETING_SUBJECT = "New Meeting";

    private MeetingListActivity mActivity;

    @Rule
    public ActivityTestRule<MeetingListActivity> mActivityRule =
            new ActivityTestRule(MeetingListActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void A_myMeetingsList_shouldNotBeEmpty() {
        onView(ViewMatchers.withId(R.id.list_meetings))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void B_myMeetingsList_deleteAction_shouldRemoveItem() {
        onView(ViewMatchers.withId(R.id.list_meetings))
                .check(withItemCount(ITEMS_COUNT));
        onView(ViewMatchers.withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void C_myMeetingsList_addAction_shouldAddItem() {
        onView(ViewMatchers.withId(R.id.list_meetings))
                .check(withItemCount(ITEMS_COUNT-1));
        onView(ViewMatchers.withId(R.id.add_meeting)).perform(click());
        onView(ViewMatchers.withId(R.id.meeting_title)).perform(typeText(NEW_MEETING_SUBJECT), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.meeting_all_attendees)).perform(typeText("a@a.fr"), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.create)).perform(click());
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(ITEMS_COUNT));
    }

    @Test
    public void D_myMeetingsList_searchAction_shouldSearchItem() {
        onView(ViewMatchers.withId(R.id.list_meetings))
                .check(withItemCount(ITEMS_COUNT));
        onView(ViewMatchers.withId(R.id.search_btn)).perform(click());
        onView(ViewMatchers.withId(R.id.search_src_text)).perform(typeText(NEW_MEETING_SUBJECT));
        onView(ViewMatchers.withId(R.id.list_meetings)).check(matches(hasMinimumChildCount(1)));
    }

}