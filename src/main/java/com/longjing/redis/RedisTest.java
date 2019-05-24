package com.longjing.redis;

import com.longjing.utils.Util;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by 18746 on 2019/3/25.
 */
public class RedisTest {

    public static void main(String[] args){
//        Jedis jedis=new Jedis("localhost");
//        jedis.lpush("name","liuqian");
//        jedis.lpush("name","qiaofeng");
//        jedis.lpush("name","duanyu");
//        jedis.set("key","liuqianqian");
//        System.out.println(jedis.get("key"));
//        List<String> list=jedis.lrange("name",0,2);
//        for (String s:list
//             ) {
//            System.out.println("+++++++++"+s);
//        }
//
//        System.out.println(jedis.rpop("name"));
//        String a="GQ010100";
//        System.out.println(a.substring(a.length()-2));
        String s="123456";
        System.out.println(Util.md5(s));
    }

}
