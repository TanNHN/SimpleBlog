/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tan
 */
public class PostDTO implements Serializable{

    private int id, status;
    private String title, content, shortDescription;
    private Date date;
    private AccountDTO account;
    private List<CommentDTO> comment;

    public PostDTO(int id, String title, String shortDescription, Date date, AccountDTO account) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.date = date;
        this.account = account;
    }

    public PostDTO(int id, int status, String title, String content, String shortDescription, Date date, AccountDTO account, List<CommentDTO> comment) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.content = content;
        this.shortDescription = shortDescription;
        this.date = date;
        this.account = account;
        this.comment = comment;
    }

    
    
    public PostDTO(int id, String title, String content, String shortDescription, Date date, AccountDTO account, List<CommentDTO> comment) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.shortDescription = shortDescription;
        this.date = date;
        this.account = account;
        this.comment = comment;
    }

    public PostDTO(int id, String title, String content, String shortDescription, Date date, AccountDTO account) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.shortDescription = shortDescription;
        this.date = date;
        this.account = account;
    }

    public List<CommentDTO> getComment() {
        return comment;
    }

    public void setComment(List<CommentDTO> comment) {
        this.comment = comment;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
