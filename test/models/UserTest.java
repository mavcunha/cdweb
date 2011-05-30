package models;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

public class UserTest extends UnitTest {

    @Before
    public void setUp() throws Exception {
        new User("Test User","testuser","secret").save();
    }

    @Test
    public void createAndRetrieveUser() {
        User user = User.find("byUsername", "testuser").first();
        assertThat(user.fullname,is("Test User"));
        assertThat(user.username,is("testuser"));
        assertThat(user.password,is("secret"));
    }

    @Test
    public void retrieveANonExistentUser() {
        assertNull(User.find("byUsername", "nonexistentuser").<User>first());
    }

    @Test
    public void userCanConnectGivenRightPassword() {
        assertThat(User.connect("testuser", "secret"),is(instanceOf(User.class)));
    }

    @Test
    public void userCannotConnectGivenWrongPassword() {
        assertNull(User.connect("testuser","wrongpassword"));
    }
}
