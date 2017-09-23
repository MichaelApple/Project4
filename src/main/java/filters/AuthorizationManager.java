package filters;

import model.entities.User;

/**
 * Created by Miha on 20.09.2017.
 */
public interface AuthorizationManager {
    public boolean isUserAuthorized(User user, String uri);
}
