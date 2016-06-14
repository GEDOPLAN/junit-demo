package de.gedoplan.webclients.testhelper;

import de.gedoplan.webclients.model.User;
import de.gedoplan.webclients.service.UserServiceInterface;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

/**
 * Unser "echter" UserService verwendet den Request-Context der Webanwendung um
 * auf den aktuellen User zugreifeb zu können. Unsere Tests setzen auf
 * Service-Level auf und laufen somit ohne Request > wir benötigen einen
 * alternativen Ansatz um den aktuellen Benutzer zu liefern.
 *
 * Bei dieser Klasse handelt es sich um eine "Alterantive" die in der beans.xml
 * bei unseren Tests aktiviert wird.
 *
 * @author Dominik Mathmann
 */
@Alternative
@Stateless
public class MockedUserService implements UserServiceInterface {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User loadUser() {
        return this.user;
    }
}
