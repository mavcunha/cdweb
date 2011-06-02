package security;

import org.junit.Test;
import play.test.UnitTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AuthenticatorTest extends UnitTest {
    @Test
    public void canAuthenticate() {
        assertTrue(Authenticator.authenticate("mavcunha", "E^*]{2T6]3P26TX"));
        assertFalse(Authenticator.authenticate("mavcunha","wrongpassword"));
        assertFalse(Authenticator.authenticate("wronguser","E^*]{2T6]3P26TX"));
        assertFalse(Authenticator.authenticate("mavcunha",""));
        assertFalse(Authenticator.authenticate(null,"blah"));
        assertFalse(Authenticator.authenticate("wrong",null));
    }
}
