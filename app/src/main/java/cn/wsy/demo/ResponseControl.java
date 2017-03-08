package cn.wsy.demo;

/**
 * Created by Yuan on 2017/3/7.
 * Detail
 */

public class ResponseControl {

    private int expires;

    public ResponseControl() {
    }

    public ResponseControl(int expires) {
        this.expires = expires;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }
}
