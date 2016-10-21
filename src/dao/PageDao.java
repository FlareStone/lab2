package dao;

import java.sql.*;
import java.util.*;

import entity.Author;
import org.DatabaseConn;
import entity.Book;

/**
 * Created by 54333 on 2016/10/12.
 */
public class PageDao {
    public  List<Book> allBooks(int pageNumber, int pageSize, String author) {
        int authorID = findID(author);
        List<Book> list = new ArrayList<>();
        try {
            Connection con = DatabaseConn.getConnection();
            PreparedStatement ps;
            if(author == null || author.equals("")) {
                String sql = "select * from book limit ?,?";
                ps = con.prepareStatement(sql);
                ps.setInt(1,(pageNumber-1)*pageSize);
                ps.setInt(2,pageSize);
            }else {
                String sql = "select * from book where AuthorID = ? limit ?,?";
                ps = con.prepareStatement(sql);
                ps.setInt(1,authorID);
                ps.setInt(2,(pageNumber-1)*pageSize);
                ps.setInt(3,pageSize);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString(1));
                book.setTitle(rs.getString(2));
                book.setAuthor(findAuthor(rs.getInt(3)));
                book.setPublisher(rs.getString(4));
                book.setPublishDate(rs.getString(5));
                book.setPrice(rs.getDouble(6));
                list.add(book);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Author> allAuthors() {
        List<Author> list = new ArrayList<>();
        try {
            Connection con = DatabaseConn.getConnection();
            String sql = "select * from author";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Author author = new Author();
                author.setAuthorID(rs.getInt(1));
                author.setName(rs.getString(2));
                author.setAge(rs.getInt(3));
                author.setCountry(rs.getString(4));
                list.add(author);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getBookAmount(String author) {
        int i = 0;
        int authorID = findID(author);
        try {
            Connection con = DatabaseConn.getConnection();
            PreparedStatement ps;
            if(author == null || author.equals("")) {
                String sql = "select count(*) from book";
                ps = con.prepareStatement(sql);
            }else {
                String sql = "select count(*) from book where AuthorID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1,authorID);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                i = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public int findID(String author) {
        int authorID = 0;
        try {
            Connection con = DatabaseConn.getConnection();
            String sql = "select * from author where name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, author);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                authorID = rs.getInt(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return authorID;
    }

    public String findAuthor(int authorID) {
        String author = "";
        try {
            Connection con = DatabaseConn.getConnection();
            String sql = "select * from author where authorID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, authorID);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                author = rs.getString(2);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }
}
