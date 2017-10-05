package filters;


import javax.servlet.annotation.WebFilter;


/**
 * Created by Miha on 05.09.2017.
 */
@WebFilter(filterName = "ContentTypeFilter", urlPatterns = "/*")
public class ContentTypeFilter implements javax.servlet.Filter {

    public void destroy() {
    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, java.io.IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
        chain.doFilter(req, resp);
    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {

    }

}
