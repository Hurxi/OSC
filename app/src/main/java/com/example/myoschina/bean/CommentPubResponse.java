package com.example.myoschina.bean;

/**
 * Created by 若希 on 2017/5/8.
 */

public class CommentPubResponse {

    /**
     * error_description : 操作成功完成
     * error : 200
     */

    private String error_description;
    private String error;

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
