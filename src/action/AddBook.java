package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.PageDao;
import entity.Author;
import org.DatabaseConn;
import org.apache.struts2.ServletActionContext;
import sun.security.krb5.internal.AuthorizationData;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 54333 on 2016/10/14.
 */
public class AddBook extends ActionSupport {
    private String isbn;
    private String title;
    private int authorID;
    private String publisher;
    private String publishDate;
    private double price;
    private List<Author> allAuthors;
    private String url;
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Author> getAllAuthors() {
        return allAuthors;
    }

    public void setAllAuthors(List<Author> allAuthors) {
        this.allAuthors = allAuthors;
    }

    @Override
    public String execute() throws Exception {
        PageDao pd = new PageDao();
        allAuthors = pd.allAuthors();
        if(flag.equals("1")) {
            ActionContext ctx = ActionContext.getContext();
            HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
            url = request.getHeader("referer");
        }
        return SUCCESS;
    }

    public String submitNewBook() throws Exception {
        Connection con = DatabaseConn.getConnection();
        String sql = "INSERT INTO book VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,isbn);
        ps.setString(2,title);
        ps.setInt(3,authorID);
        ps.setString(4,publisher);
        ps.setString(5,publishDate);
        ps.setDouble(6,price);
        ps.execute();
        return SUCCESS;
    }
}
