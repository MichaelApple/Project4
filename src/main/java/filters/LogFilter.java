package filters;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebFilter;


/**
 * Created by Miha on 05.09.2017.
 */
@WebFilter(filterName = "LogFilter")
public class LogFilter implements javax.servlet.Filter {

    //private final static Logger logger = Logger.getLogger(LogFilter.class);

    public void destroy() {
    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, java.io.IOException {

        //logger.info("This is my first log message");
        chain.doFilter(req, resp);
    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {

    }

}
