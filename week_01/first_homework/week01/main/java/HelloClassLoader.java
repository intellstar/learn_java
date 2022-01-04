package main.java;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader  extends ClassLoader{


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(".\\main\\java\\Hello.xlass");
            byte[] bytes = IOUtils.toByteArray(fis);
            for (int i =0;i<bytes.length;i++) {
                bytes[i] = (byte)((byte)255 - bytes[i]);
            }
            return defineClass(name, bytes,0,bytes.length);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return super.loadClass(name);
    }

    public static void main(String[] args) {
        try {
            Class<?> clazz = new HelloClassLoader().findClass("Hello");
            Object  o= clazz.newInstance(); // 加载并初始化Hello类
            Method hello = clazz.getMethod("hello",null);
            hello.invoke(o,null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
