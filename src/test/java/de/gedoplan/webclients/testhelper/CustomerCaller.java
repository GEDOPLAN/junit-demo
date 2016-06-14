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
@RunAs("customer")
@PermitAll
public class CustomerCaller {

    @Inject
    UserServiceInterface userService;

    public <V> V call(Callable<V> callable) throws Exception {
        userService.setUser(CustomerCaller.getUser());

        return callable.call();
    }

    public static User getUser() {
        User user = new User();
        user.setCustomerID("ALFAA");
        user.setLogin("ALFAA");
        user.setRoles(Collections.singletonList("customer"));

        return user;
    }
}
