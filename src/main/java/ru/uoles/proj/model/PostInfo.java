package ru.uoles.proj.model;

import java.io.File;
import java.util.List;

public class PostInfo {

    private String caption;
    private List<File> listPhoto;
    private String fromDate;

    public PostInfo() {
    }

    public PostInfo(String caption, List<File> listPhoto, String fromDate) {
        this.caption = caption;
        this.listPhoto = listPhoto;
        this.fromDate = fromDate;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<File> getListPhoto() {
        return listPhoto;
    }

    public void setListPhoto(List<File> listPhoto) {
        this.listPhoto = listPhoto;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
}
