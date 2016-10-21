package action;

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


/**
 * Created by 54333 on 2016/10/13.
 */
public class ShowDetail extends ActionSupport{
    private String isbn;
    private Author author;
    private Book book;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String execute() throws Exception {
        PageDao pd = new PageDao();
        Connection con = DatabaseConn.getConnection();
        String sql = "select * from book where isbn = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, isbn);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            book = new Book();
            book.setIsbn(rs.getString(1));
            book.setTitle(rs.getString(2));
            book.setAuthor(pd.findAuthor(rs.getInt(3)));
            book.setPublisher(rs.getString(4));
            book.setPublishDate(rs.getString(5));
            book.setPrice(rs.getDouble(6));
        }
        sql = "select * from author where authorID = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, pd.findID(book.getAuthor()));
        rs = ps.executeQuery();
        if (rs.next()) {
            author = new Author();
            author.setAuthorID(rs.getInt(1));
            author.setName(rs.getString(2));
            author.setAge(rs.getInt(3));
            author.setCountry(rs.getString(4));
        }
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
        url = request.getHeader("referer");
        return SUCCESS;
    }
}
