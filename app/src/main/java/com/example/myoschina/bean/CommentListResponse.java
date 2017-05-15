package com.example.myoschina.bean;

import java.util.List;

/**
 * Created by 若希 on 2017/5/8.
 */

public class CommentListResponse {

    /**
     * commentList : [{"replies":[],"commentPortrait":"https://static.oschina.net/uploads/user/63/126649_50.gif?t=1385438587000","commentAuthorId":126649,"commentAuthor":"红星xx","id":381208,"client_type":1,"pubDate":"2012-12-24 16:30:36","content":"暂时stop所有服务 ，tar zcvf 打个包 。 直接 wget&nbsp; ssh:xxxx \n<a href=\"http://my.oschina.net/xrf116\">@xxx<\/a> /xxx"},{"replies":[{"rpubDate":"2012-12-24 16:33:08","rcontent":"谢谢。","rauthorId":211822,"rauthor":"功夫panda"}],"commentPortrait":"https://static.oschina.net/uploads/user/136/273036_50.jpg?t=1386226162000","commentAuthorId":273036,"commentAuthor":"酒逍遥","id":381145,"client_type":1,"pubDate":"2012-12-24 15:47:49","content":"linux 可以直接通过nfs 把windows 的某个目录挂载过来.   程序里面 只需执行复制操作 就可以了..   例如 linux 上传目录 是 /tmp &nbsp;windows下把 E:\\upload 目录通过nfs挂载到 linux的   /upload 目录下   程序里面就只需要 把文件从 /tmp 目录复制到 /upload 目录即可.   这个实现起来应该比较简单."},{"replies":[],"commentPortrait":"https://static.oschina.net/uploads/user/45/91334_50.jpeg?t=1486970709000","commentAuthorId":91334,"commentAuthor":"yuzhouliu","id":381121,"client_type":1,"pubDate":"2012-12-24 15:29:11","content":"没啥搞头，建立共享就行"},{"replies":[{"rpubDate":"2012-12-24 15:03:28","rcontent":"嗯，我也这么实现了、","rauthorId":211822,"rauthor":"功夫panda"}],"commentPortrait":"https://static.oschina.net/uploads/user/62/124879_50.png?t=1405988930000","commentAuthorId":124879,"commentAuthor":"Yisen","id":381094,"client_type":1,"pubDate":"2012-12-24 15:01:30","content":"要程序实现就用现成的ftp库或者http上下传就好了"},{"replies":[{"rpubDate":"2012-12-25 09:37:20","rcontent":"<a href=\"http://my.oschina.net/meSpace\">@monkey~<\/a> 复制粘贴也是用程序做啊","rauthorId":232085,"rauthor":"Frank_mc"},{"rpubDate":"2012-12-24 15:04:17","rcontent":"不能用复制黏贴，只能用程序实现。我用了ftpclient这个。可以实现。","rauthorId":211822,"rauthor":"功夫panda"}],"commentPortrait":"https://static.oschina.net/uploads/user/116/232085_50.jpg?t=1434370127000","commentAuthorId":232085,"commentAuthor":"Frank_mc","id":381077,"client_type":1,"pubDate":"2012-12-24 14:52:57","content":"windows 做个nfs的盘，linux挂载下，直接复制粘贴就可以了"},{"replies":[],"commentPortrait":"https://static.oschina.net/uploads/user/78/156174_50.jpg?t=1373419221000","commentAuthorId":156174,"commentAuthor":"dedenj","id":380939,"client_type":1,"pubDate":"2012-12-24 13:12:50","content":"samba，直接挂共享目录"},{"replies":[{"rpubDate":"2012-12-24 13:17:05","rcontent":"我也打算用这个试试，但是一旦批量上传的话...","rauthorId":211822,"rauthor":"功夫panda"}],"commentPortrait":"https://static.oschina.net/uploads/user/60/120166_50.jpg?t=1370423957000","commentAuthorId":120166,"commentAuthor":"陶邦仁","id":380937,"client_type":1,"pubDate":"2012-12-24 13:12:34","content":"FTPClient 就可以"},{"replies":[],"commentPortrait":"https://static.oschina.net/uploads/user/47/95703_50.jpeg?t=1487813449000","commentAuthorId":95703,"commentAuthor":"厦门萝卜","id":380930,"client_type":1,"pubDate":"2012-12-24 13:09:03","content":"SFTP &nbsp;直接搞！"},{"replies":[{"rpubDate":"2012-12-24 13:46:24","rcontent":"回复 \n<a href=\"http://my.oschina.net/datasys\">@GNU/数据<\/a> : 谢谢。","rauthorId":211822,"rauthor":"功夫panda"},{"rpubDate":"2012-12-24 13:29:47","rcontent":"那就是C/S模型，将Windows架设成服务器端，将Linux做成客户端。服务器软件和客户端软件就看你自己的习惯了，架构的方法有很多","rauthorId":858307,"rauthor":"没文化的学霸"},{"rpubDate":"2012-12-24 13:16:24","rcontent":"回复 \n<a href=\"http://my.oschina.net/datasys\">@GNU/数据<\/a> : 工作在linux中，文件放到window服务器中。","rauthorId":211822,"rauthor":"功夫panda"},{"rpubDate":"2012-12-24 13:14:21","rcontent":"在这两个服务器中，你的工作平台选择Linux的那台还是Windows的那台？","rauthorId":858307,"rauthor":"没文化的学霸"},{"rpubDate":"2012-12-24 13:11:42","rcontent":"winscp不行。我只能在程序中实现。","rauthorId":211822,"rauthor":"功夫panda"}],"commentPortrait":"https://static.oschina.net/uploads/user/429/858307_50.jpg?t=1351398843000","commentAuthorId":858307,"commentAuthor":"没文化的学霸","id":380924,"client_type":3,"pubDate":"2012-12-24 13:06:46","content":"最好用WinSCP"},{"replies":[{"rpubDate":"2012-12-24 13:03:38","rcontent":"主要是用程序实现啊。真不想太折腾啊。","rauthorId":211822,"rauthor":"功夫panda"}],"commentPortrait":"https://www.oschina.net/img/portrait.gif","commentAuthorId":875871,"commentAuthor":"Duke.Yee","id":380913,"client_type":1,"pubDate":"2012-12-24 13:01:20","content":"如果只是文件，而不是数据的，我一般想到的就是samba和ftp，后者权限控制更全面一些。   至于webservice和xmlhttp，我想能不写代码最好就别折腾了吧"},{"replies":[],"commentPortrait":"https://static.oschina.net/uploads/user/62/124879_50.png?t=1405988930000","commentAuthorId":124879,"commentAuthor":"Yisen","id":380897,"client_type":1,"pubDate":"2012-12-24 12:51:52","content":"samba"},{"replies":[],"commentPortrait":"https://static.oschina.net/uploads/user/50/101769_50.jpg?t=1400488544000","commentAuthorId":101769,"commentAuthor":"标中堂","id":380879,"client_type":2,"pubDate":"2012-12-24 12:40:23","content":"winscp,很方便，或者直接让linux启动samba共享，更方便。"},{"replies":[{"rpubDate":"2012-12-24 12:54:20","rcontent":"其实你没明白我的意思。项目在linux，项目中有上传文件功能，上传文件你放到window服务器上（可以批量上传）。","rauthorId":211822,"rauthor":"功夫panda"}],"commentPortrait":"https://static.oschina.net/uploads/user/41/82993_50.png?t=1402714881000","commentAuthorId":82993,"commentAuthor":"王振威","id":380857,"client_type":1,"pubDate":"2012-12-24 12:25:01","content":"scp"},{"replies":[],"commentPortrait":"https://static.oschina.net/uploads/user/109/219402_50.jpg?t=1361842953000","commentAuthorId":219402,"commentAuthor":"滔滔下载站","id":380851,"client_type":1,"pubDate":"2012-12-24 12:12:22","content":"git bash 直接scp搞定"},{"replies":[{"rpubDate":"2012-12-24 12:52:05","rcontent":"没有要求，就是不知道怎么实现。","rauthorId":211822,"rauthor":"功夫panda"}],"commentPortrait":"https://static.oschina.net/uploads/user/8/17793_50.jpg?t=1388627886000","commentAuthorId":17793,"commentAuthor":"ddatsh","id":380843,"client_type":1,"pubDate":"2012-12-24 11:59:50","content":"要求咋实现就咋实现"}]
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
         * replies : []
         * commentPortrait : https://static.oschina.net/uploads/user/63/126649_50.gif?t=1385438587000
         * commentAuthorId : 126649
         * commentAuthor : 红星xx
         * id : 381208
         * client_type : 1
         * pubDate : 2012-12-24 16:30:36
         * content : 暂时stop所有服务 ，tar zcvf 打个包 。 直接 wget&nbsp; ssh:xxxx
         <a href="http://my.oschina.net/xrf116">@xxx</a> /xxx
         */

        private String commentPortrait;
        private int commentAuthorId;
        private String commentAuthor;
        private int id;
        private int client_type;
        private String pubDate;
        private String content;
        private List<?> replies;

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

        public List<?> getReplies() {
            return replies;
        }

        public void setReplies(List<?> replies) {
            this.replies = replies;
        }
    }
}
