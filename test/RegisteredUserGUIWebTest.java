package test;

import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import org.junit.Test;

public class RegisteredUserGUIWebTest {

    private WebTester tester;

    @Before
    public void prepare() {
        tester = new WebTester();
        tester.setBaseUrl("http://localhost:8080/MRA/");
    }

    @Test
    public void testBrowseHolidayOffers() {
        tester.beginAt("webpageRegisteredUser");

        tester.assertTitleEquals("Existing User");
        tester.assertFormPresent();
        tester.assertTextPresent("Required Information");
        tester.assertTextPresent("Title");
        tester.assertFormElementPresent("title");
        tester.assertTextPresent("Actor");
        tester.assertFormElementPresent("actor");
        tester.assertTextPresent("Director");
        tester.assertFormElementPresent("director");
        tester.assertTextPresent("publishedDate");
        tester.assertFormElementPresent("publishedDate");
        tester.assertButtonPresent("SelectRUWebpage");


        tester.setTextField("title", "myTitle");
        tester.setTextField("actor", "myActor");
        tester.setTextField("director", "myDirector");
        tester.setTextField("publishedDate", "06/24/2021");

        tester.clickButton("SelectRUWebpage");


        tester.assertTablePresent("availableMovies");
        String[][] tableHeadings = { { "CLICK TO MAKE RATING", "title", "actor", "director", "publishedDate" } };
        tester.assertTableEquals("availableMovies", tableHeadings);

    }
}
