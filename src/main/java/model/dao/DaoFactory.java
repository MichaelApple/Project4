package model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Miha on 15.09.2017.
 */
public abstract class DaoFactory {

    public abstract DaoConnection getConnection();
//    public abstract UserDao getUserDao();
    public abstract UserDao getUserDao(DaoConnection connection);
    public abstract BrigadeDao getRequestDao(DaoConnection connection);
    public abstract WorkPlanDao getWorkPlanDao(DaoConnection connection);


    private static DaoFactory instance;

    public static final String DB_FILE = "/db.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";

    public static DaoFactory getInstance() {
        if (instance == null) {
            try {
                InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);
                Properties dbProperties = new Properties();
                dbProperties.load(inputStream);

                instance = (DaoFactory) Class.forName(dbProperties.getProperty(DB_FACTORY_CLASS)).newInstance();

            } catch (IOException | IllegalAccessException |
                    ClassNotFoundException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


}
