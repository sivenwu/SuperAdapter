package cn.wsy.demo;

import cn.wsy.generallib.httplib.base.BaseEnetity;

/**
 * Created by wsy on 2016/8/3.
 */
public class NewEnetity extends BaseEnetity{

    @Override
    public String getRuqestURL() {
        return "http://apis.baidu.com/txapi/tiyu/tiyu";
    }
    private int num;
    private int page;

    public NewEnetity(int num, int page) {
        this.num = num;
        this.page = page;
    }

    public NewEnetity() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
