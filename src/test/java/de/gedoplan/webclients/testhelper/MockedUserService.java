package de.gedoplan.webclients.testhelper;

import de.gedoplan.webclients.model.User;
import de.gedoplan.webclients.service.UserServiceInterface;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author dmathmann
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
