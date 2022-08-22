package com.xxyf.buil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 小小怡飞
 * @Date 2022/8/4 13:47
 * @Version JDK 8
 */
public abstract class JdbcCle<T> {

    public static int size;
    public static int maxsize;
    public List<Conter<T>> jbdc;
    public void caret(T car){
        if (jbdc!=null){
            return;
        }
        jbdc = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            jbdc.add(creat());
        }
    }
    abstract Conter<T> creat();

    private class Conter<T>{
        private boolean colse=false;
        T jdbc;
        public Conter(T jdbc){
            this.jdbc=jdbc;
        }

        public boolean isColse() {
            return colse;
        }

        public void setColse(boolean colse) {
            this.colse = colse;
        }

        public T getJdbc() {
            return jdbc;
        }

        public void setJdbc(T jdbc) {
            this.jdbc = jdbc;
        }
    }
}
