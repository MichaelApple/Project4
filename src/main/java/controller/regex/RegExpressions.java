package controller.regex;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Miha on 28.07.2017.
 */
public class RegExpressions {

    private static final String NAME = "^[A-z'-]{1,20}$";
    private static final String COMMENT = "^.*$";
    private static final String PHONE = "^\\([0-9]{3}\\)[0-9]{3}-[0-9]{2}-[0-9]{2}$";
    private static final String EMAIL = "^([A-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
    private static final String SKYPE = "[a-z0-9_-]{3,20}$";
    private static final String ZIP = "^[0-9]{5}(?:-[0-9]{4})?$";
    private static final String FLAT = "^[0-9]{1,3}[A-z]?$";
    private static final String BUILDING_NUMBER = "^[0-9]{1,3}[A-z]?$";

    private static final String GROUP = "^sport|entertainment|anonymous_alcoholic$";

    public final static HashMap<String, String> REG_EXPRESSION_MAP = new LinkedHashMap<>();

    static {
        REG_EXPRESSION_MAP.put("firstName", NAME);
        REG_EXPRESSION_MAP.put("lastName", NAME);
        REG_EXPRESSION_MAP.put("comment", COMMENT);
        REG_EXPRESSION_MAP.put("homePhone", PHONE);
        REG_EXPRESSION_MAP.put("cellPhone", "^.*$");
        REG_EXPRESSION_MAP.put("email", EMAIL);
        REG_EXPRESSION_MAP.put("newEmail", EMAIL);
        REG_EXPRESSION_MAP.put("zip", ZIP);
        REG_EXPRESSION_MAP.put("city", NAME);
        REG_EXPRESSION_MAP.put("street", NAME);
        REG_EXPRESSION_MAP.put("buildingNumber", BUILDING_NUMBER);
        REG_EXPRESSION_MAP.put("flat", FLAT);

    }

    public static boolean checkData(HttpServletRequest request) {

        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();

            for (Map.Entry<String, String> entry : REG_EXPRESSION_MAP.entrySet()) {
                if (entry.getKey().equals(paramName)) {
                    String value = request.getParameter(paramName);
                        return value.matches(entry.getValue());
                }
            }
        }
        return false;
    }
}
