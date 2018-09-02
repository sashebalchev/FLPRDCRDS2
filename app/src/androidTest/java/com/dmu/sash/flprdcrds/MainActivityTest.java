package com.dmu.sash.flprdcrds;

import android.support.test.espresso.ViewFinder;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkLearningFragmentIsOpenedInitially() {
        // Check if the main frame is displayed
        onView(withId(R.id.main_frame)).check(matches(isDisplayed()));

        // Check if the navigation bar is displayed
        onView(withId(R.id.main_nav)).check(matches(isDisplayed()));

        // Check if the AdapterViewFlipper is displayed
        onView(withId(R.id.flipper_all)).check(matches(isDisplayed()));
    }

    @Test
    public void clickWordManagementMenu_opensWordManagementUi() {
        // Click on the Word Management menu
        onView(withId(R.id.nav_management)).perform(click());

        // Check if the add word button is displayed
        onView(withId(R.id.fab)).check(matches(isDisplayed()));

        // Check if the delete all words button is displayed
        onView(withId(R.id.delete_words_button)).check(matches(isDisplayed()));
    }


    @Test
    public void clickAddWordButton_opensAddWordDialog() {
        // Click on the Word Management menu
        onView(withId(R.id.nav_management)).perform(click());

        // Check if the add word button is displayed
        onView(withId(R.id.fab)).check(matches(isDisplayed()));

        // Click on the Add Word button
        onView(withId(R.id.fab)).perform(click());

        // Check if the add word dialog is displayed
        onView(withText(R.string.add_word)).check(matches(isDisplayed()));

        // Check if the add button is displayed
        onView(withText(R.string.add)).check(matches(isDisplayed()));

        // Check if the cancel button is displayed
        onView(withText(R.string.cancel)).check(matches(isDisplayed()));
    }


    @Test
    public void addingWordSound_shouldHave7Choises() {
        // Click on the Word Management menu
        onView(withId(R.id.nav_management)).perform(click());

        // Check if the add word button is displayed
        onView(withId(R.id.fab)).check(matches(isDisplayed()));

        // Click on the Add Word button
        onView(withId(R.id.fab)).perform(click());

        // Check if the add word dialog is displayed
        onView(withText(R.string.add_word)).check(matches(isDisplayed()));

        // Type the word sound
        String word = "sound";
        onView(allOf(ViewMatchers.hasFocus(), isAssignableFrom(EditText.class))).perform(typeText(word), closeSoftKeyboard());

        // Click on add button
        onView(withText(R.string.add)).perform(click());

        // Check if the select word definition dialog is displayed
        String prompt = mMainActivityTestRule.getActivity().getString(R.string.select_definition_prompt, word);
        onView(withText(prompt)).check(matches(isDisplayed()));

        // TODO - check that there are 7 choices
    }


    @Test
    public void clickDeleteAllWordsButton_opensDeleteAllWordsDialog() {
        // Click on the Word Management menu
        onView(withId(R.id.nav_management)).perform(click());

        // Check if the dlete all words button is displayed
        onView(withId(R.id.delete_words_button)).check(matches(isDisplayed()));

        // Click on the DELETE ALl WORDS button
        onView(withId(R.id.delete_words_button)).perform(click());

        // Check if the delete all words dialog is displayed
        onView(withText(R.string.delete_all_words_prompt)).check(matches(isDisplayed()));
    }


    @Test
    public void clickProfileMenu_opensProfileUi() {
        // Click on the Profile menu
        onView(withId(R.id.nav_profile)).perform(click());

        // Check if the TextView number of sessions is displayed
        onView(withId(R.id.number_sessions)).check(matches(isDisplayed()));

        // Check if the TextView mastered words is displayed
        onView(withId(R.id.mastered_words)).check(matches(isDisplayed()));

        // Check if the TextView known words is displayed
        onView(withId(R.id.known_words)).check(matches(isDisplayed()));

        // Check if the TextView struggled words is displayed
        onView(withId(R.id.struggled_words)).check(matches(isDisplayed()));
    }


    @Test
    public void clickSettingsMenu_opensSettingsUi() {
        // Click on the Settings menu
        onView(withId(R.id.nav_settings)).perform(click());

        // Check if the Background Color is displayed
        onView(withText(R.string.pref_bgcolor)).check(matches(isDisplayed()));

        // Check if the Font Color is displayed
        onView(withText(R.string.pref_fontcolor)).check(matches(isDisplayed()));

        // Check if the Font Style is displayed
        onView(withText(R.string.pref_fontstyle)).check(matches(isDisplayed()));

        // Check if the Reset is displayed
        onView(withText(R.string.pref_reset)).check(matches(isDisplayed()));
    }

}
