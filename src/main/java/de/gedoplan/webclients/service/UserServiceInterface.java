package de.gedoplan.webclients.service;

import de.gedoplan.webclients.model.User;

/**
 *
 * @author dmathmann
 */
public interface UserServiceInterface {

    User loadUser();

    void setUser(User user);
}
