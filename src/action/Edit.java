package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.PageDao;
import entity.Author;
import entity.Book;
import org.DatabaseConn;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 54333 on 2016/10/13.
 */
public class Edit extends ActionSupport {
    private String isbn;
    private String newPublisher;
    private String newPublishDate;
    private double newPrice;
    private int newAuthorID;
    private String flag;
    private int oldAuthorID;

    public int getOldAuthorID() {
        return oldAuthorID;
    }

    public void setOldAuthorID(int oldAuthorID) {
        this.oldAuthorID = oldAuthorID;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNewPublisher() {
        return newPublisher;
    }

    public void setNewPublisher(String newPublisher) {
        this.newPublisher = newPublisher;
    }

    public String getNewPublishDate() {
        return newPublishDate;
    }

    public void setNewPublishDate(String newPublishDate) {
        this.newPublishDate = newPublishDate;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public int getNewAuthorID() {
        return newAuthorID;
    }

    public void setNewAuthorID(int newAuthorID) {
        this.newAuthorID = newAuthorID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Author> getAllAuthors() {
        return allAuthors;
    }

    public void setAllAuthors(List<Author> allAuthors) {
        this.allAuthors = allAuthors;
    }

    private String url;
    private Book book;
    private String author;
    private List<Author> allAuthors;


    @Override
    public String execute() throws Exception {
        PageDao pd = new PageDao();
        Connection con = DatabaseConn.getConnection();
        String sql = "SELECT * FROM book WHERE isbn=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,isbn);
        ResultSet rs = ps.executeQuery();
        book = new Book();
        if(rs.next()) {
            book.setIsbn(rs.getString(1));
            book.setTitle(rs.getString(2));
            oldAuthorID = rs.getInt(3);
            book.setAuthor(pd.findAuthor(rs.getInt(3)));
            book.setPublisher(rs.getString(4));
            book.setPublishDate(rs.getString(5));
            book.setPrice(rs.getDouble(6));
        }
        sql = "SELECT * FROM author WHERE AuthorID=?";
        ps = con.prepareStatement(sql);
        ps.setInt(1,pd.findID(book.getAuthor()));
        rs = ps.executeQuery();
        if(rs.next()) {
            author = rs.getString(2);
        }
        allAuthors = pd.allAuthors();
        if(flag.equals("1")) {
            ActionContext ctx = ActionContext.getContext();
            HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
            url = request.getHeader("referer");
        }
        return SUCCESS;
    }

    public String submitOldBook() throws Exception {
        Connection con = DatabaseConn.getConnection();
        String sql = "UPDATE book SET AuthorID=?,Publisher=?,PublishDate=?,Price=? WHERE isbn=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,newAuthorID);
        ps.setString(2,newPublisher);
        ps.setString(3,newPublishDate);
        ps.setDouble(4,newPrice);
        ps.setString(5,isbn);
        if(flag.equals("0")) {
            Pattern p = Pattern.compile("(?<=author=).*");
            Matcher m = p.matcher(url);
            if(m.find()) {
                url = m.replaceFirst(URLEncoder.encode(m.group(), "utf-8"));
            }
        }
        return SUCCESS;
    }
}
