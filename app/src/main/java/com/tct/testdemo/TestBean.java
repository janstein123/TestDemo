package com.tct.testdemo;

/**
 * Created by tao.j on 2017/12/19.
 */

public class TestBean {
    private String version = "1.0.3";

    public String getVersion(){
        return version + ", "+new TestBean2().getVersion();
    }
}
