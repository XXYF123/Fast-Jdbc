package com.xxyf.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author 小小怡飞
 * @Date 2022/8/18 6:58
 * @Version JDK 8
 */

@ConfigurationProperties(prefix = "fast")
public class FastConfig {

    private int buffSize;

    private boolean tm;

    public int getBuffSize() {
        return buffSize;
    }

    public void setBuffSize(int buffSize) {
        this.buffSize = buffSize;
    }



    public boolean isTm() {
        return tm;
    }

    public void setTm(boolean tm) {
        this.tm = tm;
    }
}
