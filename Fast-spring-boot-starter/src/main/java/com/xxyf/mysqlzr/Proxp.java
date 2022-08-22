package com.xxyf.mysqlzr;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author 小小怡飞
 * @Date 2022/8/4 1:24
 * @Version JDK 8
 */
public class Proxp implements InvocationHandler {
    Class<?> target;
    public void setTarget(Class<?> target) {
        this.target = target;
    }
    public Object getPyxy1(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{target},this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        return null;
    }
}
