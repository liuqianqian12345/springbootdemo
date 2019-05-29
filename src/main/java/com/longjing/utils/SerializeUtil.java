package com.longjing.utils;

import java.io.*;

/**
 * Created by 18746 on 2019/5/28.
 */
public class SerializeUtil {

    public static byte[] serialize(Object object) {
        // 序列化obj
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            try {
                oos.writeObject(object);
                return bos.toByteArray();
            } finally {
                oos.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object deserialize(byte[] buf) {
        // 反序列化成一个clone对象
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(buf);
            ObjectInputStream ois = new ObjectInputStream(bis);
            try {
                return ois.readObject();
            } finally {
                ois.close();
            }
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }
}
