package com.example.myoschina.bean;

/**
 * Created by 若希 on 2017/5/12.
 */

public class PersonalResponse {

    /**
     * gender : female
     * name : 乜辞
     * location : 江苏 常州
     * id : 3453715
     * avatar : https://www.oschina.net/img/portrait.gif
     * email : 9f27becc-17df-41f6-bca2-ef46af816657
     * url : https://my.oschina.net/u/3453715
     */

    private String gender;
    private String name;
    private String location;
    private int id;
    private String avatar;
    private String email;
    private String url;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
