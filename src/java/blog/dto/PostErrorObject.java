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
public class PostErrorObject implements Serializable{

    private String titleError, contentError, shortDescriptionError;

    public PostErrorObject() {
    }
    
    public PostErrorObject(String titleError, String contentError, String shortDescriptionError) {
        this.titleError = titleError;
        this.contentError = contentError;
        this.shortDescriptionError = shortDescriptionError;
    }

    public String getTitleError() {
        return titleError;
    }

    public void setTitleError(String titleError) {
        this.titleError = titleError;
    }

    public String getContentError() {
        return contentError;
    }

    public void setContentError(String contentError) {
        this.contentError = contentError;
    }

    public String getShortDescriptionError() {
        return shortDescriptionError;
    }

    public void setShortDescriptionError(String shortDescriptionError) {
        this.shortDescriptionError = shortDescriptionError;
    }
    
    
}
