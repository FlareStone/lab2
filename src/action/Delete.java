package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.DatabaseConn;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by 54333 on 2016/10/13.
 */
public class Delete extends ActionSupport {
    private String isbn;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String execute() throws Exception {
        Connection con = DatabaseConn.getConnection();
        String sql = "delete from book where isbn = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, isbn);
        ps.execute();
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        url = request.getHeader("referer");
        return SUCCESS;
    }
}
