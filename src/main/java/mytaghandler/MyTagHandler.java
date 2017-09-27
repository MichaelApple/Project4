package mytaghandler;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Miha on 26.09.2017.
 */
public class MyTagHandler extends TagSupport {

    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();//returns the instance of JspWriter
        try {
            out.print(userName);
        } catch(Exception e){System.out.println(e);}
        return SKIP_BODY;//will not evaluate the body content of the tag
    }
}
