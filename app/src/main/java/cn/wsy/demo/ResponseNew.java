package cn.wsy.demo;

import java.util.List;

/**
 * Created by wsy on 2016/8/3.
 */
public class ResponseNew {

    private int code;
    private String msg;
    private List<NewInfo>newslist;

    public ResponseNew(int code, String msg, List<NewInfo> newslist) {
        this.code = code;
        this.msg = msg;
        this.newslist = newslist;
    }

    public ResponseNew() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewInfo> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewInfo> newslist) {
        this.newslist = newslist;
    }

    public static class NewInfo{
        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public NewInfo(String ctime, String title, String description, String picUrl, String url) {
            this.ctime = ctime;
            this.title = title;
            this.description = description;
            this.picUrl = picUrl;
            this.url = url;
        }

        public NewInfo() {
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
