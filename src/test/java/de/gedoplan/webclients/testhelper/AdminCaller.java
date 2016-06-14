package de.gedoplan.webclients.testhelper;

import de.gedoplan.webclients.model.User;
import de.gedoplan.webclients.service.UserServiceInterface;
import java.util.Collections;
import java.util.concurrent.Callable;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@RunAs("admin")
@PermitAll
public class AdminCaller {

    @Inject
    UserServiceInterface userService;

    public <V> V call(Callable<V> callable) throws Exception {
        userService.setUser(AdminCaller.getUser());

        return callable.call();
    }

    public static User getUser() {
        User user = new User();
        user.setCustomerID(null);
        user.setLogin("admin");
        user.setRoles(Collections.singletonList("admin"));

        return user;
    }
}
