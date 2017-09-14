package mytaghandler;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Miha on 14.09.2017.
 */
public class MyTagHandler extends TagSupport{
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print(Calendar.getInstance().getTime());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
