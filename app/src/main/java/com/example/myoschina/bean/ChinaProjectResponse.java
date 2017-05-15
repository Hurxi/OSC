package com.example.myoschina.bean;

import java.util.List;

/**
 * Created by 若希 on 2017/5/11.
 */

public class ChinaProjectResponse {

    /**
     * projectlist : [{"name":"DWZ","description":"DWZ富客户端框架(jQuery RIA framework...","url":"https://www.oschina.net/p/dwz"},{"name":"JFinal","description":"    JFinal 是基于 Java 语言的极速 ...","url":"https://www.oschina.net/p/jfinal"},{"name":"Dubbo","description":"Dubbo 是阿里巴巴公司开源的一个高性能优...","url":"https://www.oschina.net/p/dubbo"},{"name":"Druid","description":"Druid是一个JDBC组件，它包括三部分： ...","url":"https://www.oschina.net/p/druid"},{"name":"禅道","description":"一、禅道(ZenTao)是什么？ 禅道是第一款...","url":"https://www.oschina.net/p/zentaopms"},{"name":"ECharts","description":"ECharts是一款由百度前端技术部开发的，...","url":"https://www.oschina.net/p/echarts"},{"name":"Carbon Forum","description":"Carbon Forum，一个高性能的、高安全性的...","url":"https://www.oschina.net/p/carbon-forum"},{"name":"Dos.ORM","description":"Dos.ORM（原Hxj.Data）于2009年发布、2...","url":"https://www.oschina.net/p/dos-orm"},{"name":"fastjson","description":"fastjson 是一个性能很好的 Java 语言实...","url":"https://www.oschina.net/p/fastjson"},{"name":"JeeSite","description":"JeeSite是基于多个优秀的开源项目，高度...","url":"https://www.oschina.net/p/jeesite"},{"name":"xUtils","description":"## xUtils3简介\n* xUtils 包含了orm, ht...","url":"https://www.oschina.net/p/xutils"},{"name":"zTree","description":"zTree 是利用 JQuery 的核心代码，实现一...","url":"https://www.oschina.net/p/ztree"},{"name":"afinal","description":"Afinal简介 Afinal 是一个android的sql...","url":"https://www.oschina.net/p/afinal"},{"name":"WeUI","description":"WeUI 是由微信官方设计团队专为微信移动...","url":"https://www.oschina.net/p/weui"},{"name":"iQQ","description":"iQQ 使用Java语言跨平台开发，基于腾讯W...","url":"https://www.oschina.net/p/iqq"},{"name":"luanfa sms","description":"本软件可乱发短信\u2014\u2014乱发是开玩笑的，发...","url":"https://www.oschina.net/p/luanfa-sms"},{"name":"FastDFS","description":"FastDFS是一个开源的分布式文件系统，她...","url":"https://www.oschina.net/p/fastdfs"},{"name":"ME@deepgully","description":"ME@deepgully ME@deepgully是基于Pytho...","url":"https://www.oschina.net/p/me-deepgully"},{"name":"GAEPhotos","description":"基于GAE的网络相册,支持外链,水印,防盗链...","url":"https://www.oschina.net/p/gaephotos"},{"name":"SpringSide","description":"SpringSide是以Spring Framework为核心的...","url":"https://www.oschina.net/p/springside"}]
     * count : 8082
     */

    private int count;
    private List<ProjectlistBean> projectlist;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProjectlistBean> getProjectlist() {
        return projectlist;
    }

    public void setProjectlist(List<ProjectlistBean> projectlist) {
        this.projectlist = projectlist;
    }

    public static class ProjectlistBean {
        /**
         * name : DWZ
         * description : DWZ富客户端框架(jQuery RIA framework...
         * url : https://www.oschina.net/p/dwz
         */

        private String name;
        private String description;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
