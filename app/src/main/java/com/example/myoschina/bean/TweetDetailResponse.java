package com.example.myoschina.bean;

/**
 * Created by 若希 on 2017/5/9.
 */

public class TweetDetailResponse {

    /**
     * imgBig : https://static.oschina.net/uploads/space/2017/0509/100951_wFBZ_105890.png
     * author : poorfish
     * id : 13208852
     * portrait : https://static.oschina.net/uploads/user/52/105890_50.jpeg?t=1474192338000
     * authorid : 105890
     * body : 粉红围脖
     * pubDate : 2017-05-09 10:08:05
     * imgSmall : https://static.oschina.net/uploads/space/2017/0509/100951_wFBZ_105890_thumb.png
     * commentCount : 0
     */

    private String imgBig;
    private String author;
    private int id;
    private String portrait;
    private int authorid;
    private String body;
    private String pubDate;
    private String imgSmall;
    private int commentCount;

    public String getImgBig() {
        return imgBig;
    }

    public void setImgBig(String imgBig) {
        this.imgBig = imgBig;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImgSmall() {
        return imgSmall;
    }

    public void setImgSmall(String imgSmall) {
        this.imgSmall = imgSmall;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
