package com.example.myoschina.bean;

import java.util.List;

/**
 * Created by 若希 on 2017/5/9.
 */

public class TwwetCommentResponse {

    /**
     * commentList : [{"commentPortrait":"https://static.oschina.net/uploads/user/284/569120_50.jpg?t=1385024682000","commentAuthorId":569120,"commentAuthor":"小白小霸王","id":13221023,"client_type":0,"pubDate":"2017-05-09 19:57:36","content":"这是两码事"},{"commentPortrait":"https://static.oschina.net/uploads/user/1373/2746965_50.jpeg?t=1462027927000","commentAuthorId":2746965,"commentAuthor":"马丁的代码","id":13221013,"client_type":0,"pubDate":"2017-05-09 19:56:23","content":"这是两码事&nbsp;"},{"commentPortrait":"https://static.oschina.net/uploads/user/98/196646_50.jpg?t=1420509816000","commentAuthorId":196646,"commentAuthor":"此一生","id":13220980,"client_type":0,"pubDate":"2017-05-09 19:53:33","content":"人不是鞋，鞋子又没有情绪"}]
     * notice : {"referCount":0,"replyCount":0,"msgCount":0,"fansCount":0}
     */

    private NoticeBean notice;
    private List<CommentListBean> commentList;

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public List<CommentListBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentListBean> commentList) {
        this.commentList = commentList;
    }

    public static class NoticeBean {
        /**
         * referCount : 0
         * replyCount : 0
         * msgCount : 0
         * fansCount : 0
         */

        private int referCount;
        private int replyCount;
        private int msgCount;
        private int fansCount;

        public int getReferCount() {
            return referCount;
        }

        public void setReferCount(int referCount) {
            this.referCount = referCount;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public int getMsgCount() {
            return msgCount;
        }

        public void setMsgCount(int msgCount) {
            this.msgCount = msgCount;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }
    }

    public static class CommentListBean {
        /**
         * commentPortrait : https://static.oschina.net/uploads/user/284/569120_50.jpg?t=1385024682000
         * commentAuthorId : 569120
         * commentAuthor : 小白小霸王
         * id : 13221023
         * client_type : 0
         * pubDate : 2017-05-09 19:57:36
         * content : 这是两码事
         */

        private String commentPortrait;
        private int commentAuthorId;
        private String commentAuthor;
        private int id;
        private int client_type;
        private String pubDate;
        private String content;

        public String getCommentPortrait() {
            return commentPortrait;
        }

        public void setCommentPortrait(String commentPortrait) {
            this.commentPortrait = commentPortrait;
        }

        public int getCommentAuthorId() {
            return commentAuthorId;
        }

        public void setCommentAuthorId(int commentAuthorId) {
            this.commentAuthorId = commentAuthorId;
        }

        public String getCommentAuthor() {
            return commentAuthor;
        }

        public void setCommentAuthor(String commentAuthor) {
            this.commentAuthor = commentAuthor;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getClient_type() {
            return client_type;
        }

        public void setClient_type(int client_type) {
            this.client_type = client_type;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
