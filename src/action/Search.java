package action;

import java.util.*;
import dao.PageDao;
import entity.Book;
import com.opensymphony.xwork2.ActionSupport;


import java.sql.ResultSet;

/**
 * Created by 54333 on 2016/10/6.
 */

public class Search extends ActionSupport{
    private int pageNumber;
    private int pageSize;
    private int totalPage;
    private List<Book> allBooks;
    private String author;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(List<Book> allBooks) {
        this.allBooks = allBooks;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String execute() {
        PageDao pd = new PageDao();
        pageSize = 10;
        int bookAmount = pd.getBookAmount(author);
        this.totalPage = bookAmount % pageSize == 0 ?
                (bookAmount / pageSize) : (bookAmount / pageSize + 1);
        if (this.pageNumber <= 0)
            this.pageNumber = 1;
        if (this.pageNumber > totalPage)
            this.pageNumber = totalPage;
        allBooks = pd.allBooks(pageNumber,pageSize,author);
        return SUCCESS;
    }
}
