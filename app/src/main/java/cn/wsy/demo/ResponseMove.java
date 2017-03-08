package cn.wsy.demo;

/**
 * Created by Yuan on 2017/3/7.
 * Detail
 */

public class ResponseMove {

    private ResponseControl control;
    private int status;
    private ResponseNew data;

    public ResponseMove() {
    }

    public ResponseMove(ResponseControl control, int status, ResponseNew data) {
        this.control = control;
        this.status = status;
        this.data = data;
    }

    public ResponseControl getControl() {
        return control;
    }

    public void setControl(ResponseControl control) {
        this.control = control;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResponseNew getData() {
        return data;
    }

    public void setData(ResponseNew data) {
        this.data = data;
    }
}
