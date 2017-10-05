package filters;

import model.entities.User;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Class that verify user role
 *
 * Created by Miha on 20.09.2017.
 * @author Miha
 */
public class AuthorizationManagerDefaultImpl implements AuthorizationManager {
    private Properties roleMappings;
    private static final String MAPPING_PROPERTIES = "/mapping.properties";

    /**Load mappings from a properties file on the file system.*/
    public AuthorizationManagerDefaultImpl() {

        this.roleMappings = new Properties();
        try {
            InputStream inputStream = AuthorizationManagerDefaultImpl.class.getResourceAsStream(MAPPING_PROPERTIES);
            this.roleMappings.load(inputStream);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns boolean indicating whether user has the appropriate role
     * for the specified URI.
     */
    @Override
    public boolean isUserAuthorized(User user, String uri) {

        boolean matchFound;
        boolean isAuthorized = false;

        Iterator i = roleMappings.entrySet().iterator();

        //Loop through each URI mapping and check user's roles.
        //Exit once match is found.
        while( (!isAuthorized)  &&  (i.hasNext()) ) {
            Map.Entry me = (Map.Entry)i.next();

            uri = uri.replaceAll("/Project4_war_exploded", "").replaceAll("/pages", "");

            String[] allowedPages = ((String)me.getValue()).split(", ");
            for (String pattern : allowedPages) {
                matchFound = Pattern.matches(pattern, uri);
                if(matchFound && user.getRole().toString().equals(me.getKey())) {
                    isAuthorized = true;
                    break;
                }
            }
        }
        return isAuthorized;
    }
}
