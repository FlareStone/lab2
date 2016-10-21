package action;

import com.opensymphony.xwork2.ActionSupport;
import org.DatabaseConn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.PageDao;
import entity.Author;
import entity.Book;
import org.DatabaseConn;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.Connection;

/**
 * Created by 54333 on 2016/10/14.
 */
public class AddAuthor extends ActionSupport {
    private String url;
    private String name;
    private int age;
    private String country;
    private String url2;

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        url2 = request.getHeader("referer");
        return SUCCESS;
    }

    public String submitAuthor() throws Exception {
        Connection con = DatabaseConn.getConnection();
        String sql = "INSERT INTO author VALUES  (NULL,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,name);
        ps.setInt(2,age);
        ps.setString(3,country);
        ps.execute();
        url2 = url2.replace("flag=1","flag=0");
        return SUCCESS;
    }
}
