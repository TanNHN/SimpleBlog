/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dto;

import java.io.Serializable;

/**
 *
 * @author Tan
 */
public class CommentDTO implements Serializable{
    private int id, postID;
    private String content, accountID;

    public CommentDTO(String content, String accountID) {
        this.content = content;
        this.accountID = accountID;
    }

    public CommentDTO(int id, String content, String accountID) {
        this.id = id;
        this.content = content;
        this.accountID = accountID;
    }

    
    
    public CommentDTO(int id, int postID, String content, String accountID) {
        this.id = id;
        this.postID = postID;
        this.content = content;
        this.accountID = accountID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
    
    
    
}
