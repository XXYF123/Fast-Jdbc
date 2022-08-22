package com.xxyf.mysqlzr;

import com.xxyf.mysqljdbc.Fast;
import com.xxyf.note.FastInsert;
import com.xxyf.note.FastSelect;
import com.xxyf.tools.Strif;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

/**
 * @Author 小小怡飞
 * @Date 2022/8/12 6:27
 * @Version JDK 8
 */
public class FastProxp<T> implements InvocationHandler {
    Class<T> target;
    Fast mzr;
    public FastProxp(Class<T> target, Fast mzr){
        this.target=target;
        this.mzr=mzr;
    }
    public void setTarget(Class<T> target) {
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
        return note(method,mzr,args);
    }
    public <T> T note(Method method, Fast mzr, Object... param) {

        FastSelect annotation = method.getAnnotation(FastSelect.class);
        if (annotation == null) {
            FastInsert annotation1 = method.getAnnotation(FastInsert.class);
            if (annotation1 == null) {
                return null;
            }
            String value = annotation1.value();

            return (T) Integer.valueOf(mzr.sqlupdata(value, param));

        }
        String value = annotation.value();
        if (!Strif.strnull(value)) {
            return null;
        }
        String[] key = annotation.key();
        String[] Value = annotation.Value();
        Map<String, String> map = null;
        if (Strif.arrnull(key) && Strif.arrnull(Value)) {
            for (int i = 0; i < key.length; i++) {
                map.put(key[i], Value[i]);
            }
        }
        method.setAccessible(true);
        Class type = method.getReturnType();

        if (String.class.isAssignableFrom(type)) {
            T indiv = (T) mzr.indiv(value, param);
            return indiv;
        } else if (List.class.isAssignableFrom(type)) {
            Type genericType = method.getGenericReturnType();
            Class fieldArgClass = null;
            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                // 获取成员变量的泛型类型信息
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    fieldArgClass = (Class) actualTypeArgument;

                }
            }
            T into = (T) mzr.listinto(fieldArgClass, value, map, param);
            return into;
        } else if (String[].class.isAssignableFrom(type)) {
            T indivarr = (T) mzr.indivarr(value, param);
            return indivarr;
        } else {
            return (T) mzr.into(type, value, map, param);
        }
    }
}
