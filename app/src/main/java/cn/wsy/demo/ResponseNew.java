package cn.wsy.demo;

import java.util.List;

/**
 * Created by wsy on 2016/8/3.
 */
public class ResponseNew {

    private boolean hasNext;
    private List<NewInfo>movies;

    public ResponseNew() {
    }


    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<NewInfo> getMovies() {
        return movies;
    }

    public void setMovies(List<NewInfo> movies) {
        this.movies = movies;
    }

    public static class NewInfo{

        private String showInfo;
        private int sn;
        private int cnms;
        private boolean late;
        private String img;
        private int sc;
        private String ver;
        private String rt;
        private String vd;
        private String dir;
        private String star;
        private String cat;
        private int wish;
        private String scm;
        private String showDate;
        private int pn;
        private boolean imax;
        private int snum;
        private int dur;
        private String nm;
        private int preSale;
        private String src;
        private String time;
        private String id;
        private boolean d;


        public NewInfo() {
        }

        public NewInfo(String showInfo, int sn, int cnms, boolean late, String img, int sc, String ver, String rt, String vd, String dir, String star, String cat, int wish, String scm, String showDate, int pn, boolean imax, int snum, int dur, String nm, int preSale, String src, String time, String id, boolean d) {
            this.showInfo = showInfo;
            this.sn = sn;
            this.cnms = cnms;
            this.late = late;
            this.img = img;
            this.sc = sc;
            this.ver = ver;
            this.rt = rt;
            this.vd = vd;
            this.dir = dir;
            this.star = star;
            this.cat = cat;
            this.wish = wish;
            this.scm = scm;
            this.showDate = showDate;
            this.pn = pn;
            this.imax = imax;
            this.snum = snum;
            this.dur = dur;
            this.nm = nm;
            this.preSale = preSale;
            this.src = src;
            this.time = time;
            this.id = id;
            this.d = d;
        }

        public String getShowInfo() {
            return showInfo;
        }

        public void setShowInfo(String showInfo) {
            this.showInfo = showInfo;
        }

        public boolean isLate() {
            return late;
        }

        public void setLate(boolean late) {
            this.late = late;
        }

        public int getCnms() {
            return cnms;
        }

        public void setCnms(int cnms) {
            this.cnms = cnms;
        }

        public int getSn() {
            return sn;
        }

        public void setSn(int sn) {
            this.sn = sn;
        }

        public String getShowDate() {
            return showDate;
        }

        public void setShowDate(String showDate) {
            this.showDate = showDate;
        }

        public int getDur() {
            return dur;
        }

        public void setDur(int dur) {
            this.dur = dur;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getSc() {
            return sc;
        }

        public void setSc(int sc) {
            this.sc = sc;
        }

        public String getVer() {
            return ver;
        }

        public void setVer(String ver) {
            this.ver = ver;
        }

        public String getRt() {
            return rt;
        }

        public void setRt(String rt) {
            this.rt = rt;
        }

        public String getNm() {
            return nm;
        }

        public void setNm(String nm) {
            this.nm = nm;
        }

        public String getScm() {
            return scm;
        }

        public void setScm(String scm) {
            this.scm = scm;
        }

        public boolean isImax() {
            return imax;
        }

        public void setImax(boolean imax) {
            this.imax = imax;
        }

        public int getSnum() {
            return snum;
        }

        public void setSnum(int snum) {
            this.snum = snum;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public int getPn() {
            return pn;
        }

        public void setPn(int pn) {
            this.pn = pn;
        }

        public int getPreSale() {
            return preSale;
        }

        public void setPreSale(int preSale) {
            this.preSale = preSale;
        }

        public String getVd() {
            return vd;
        }

        public void setVd(String vd) {
            this.vd = vd;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getCat() {
            return cat;
        }

        public void setCat(String cat) {
            this.cat = cat;
        }

        public int getWish() {
            return wish;
        }

        public void setWish(int wish) {
            this.wish = wish;
        }

        public boolean isD() {
            return d;
        }

        public void setD(boolean d) {
            this.d = d;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
