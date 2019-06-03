package com.longjing.redis;

import com.longjing.utils.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Created by 18746 on 2019/5/24.
 */
@Component
public class JedisClient {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 维护一个本类的静态变量
    private static JedisClient jedisClient;

    @PostConstruct
    public void init() {
        jedisClient = this;
        jedisClient.redisTemplate = this.redisTemplate;
        System.out.println("redis is ok"+redisTemplate);
    }

    /**
     * 将参数中的字符串值设置为键的值，不设置过期时间
     *
     * @param key
     * @param value 必须要实现 Serializable 接口
     */
    public static void set(String key, String value) {
        jedisClient.redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 将参数中的字符串值设置为键的值，设置过期时间
     *
     * @param key
     * @param value   必须要实现 Serializable 接口
     * @param timeout
     */
    public static void set(String key, String value, Long timeout) {
        jedisClient.redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取与指定键相关的值
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return jedisClient.redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置某个键的过期时间
     *
     * @param key 键值
     * @param ttl 过期秒数
     */
    public static boolean expire(String key, Long ttl) {
        return jedisClient.redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
    }

    /**
     * 判断某个键是否存在
     *
     * @param key 键值
     */
    public static boolean hasKey(String key) {
        return jedisClient.redisTemplate.hasKey(key);
    }

    /**
     * 向集合添加元素
     *
     * @param key
     * @param value
     * @return 返回值为设置成功的value数
     */
    public static Long sAdd(String key, String... value) {
        return jedisClient.redisTemplate.opsForSet().add(key, value);
    }


    /**
     * 将给定分数的指定成员添加到键中存储的排序集合中
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    public static Boolean zAdd(String key, String value, double score) {
        return jedisClient.redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 返回指定排序集中给定成员的分数
     *
     * @param key
     * @param value
     * @return
     */
    public static Double zScore(String key, String value) {
        return jedisClient.redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 删除指定的键
     *
     * @param key
     * @return
     */
    public static void delete(String key) {
        jedisClient.redisTemplate.delete(key);
    }

    /**
     * 删除多个键
     *
     * @param keys
     * @return
     */
    public static void delete(Collection<String> keys) {
        jedisClient.redisTemplate.delete(keys);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public static boolean hset(String key, String item, Object value) {
        try {
            jedisClient.redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public static boolean hset(String key, String item, Object value, long time) {
        try {
            jedisClient.redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key  键
     * @param item 项
     */
    public static boolean hasHash(String key, String item) {
        return jedisClient.redisTemplate.opsForHash().hasKey(key, item);
    }


    public static Object getObject(String key) {
        return jedisClient.redisTemplate.opsForValue().get(key);
    }

    public static void setObject(String key, Object o) {
        jedisClient.redisTemplate.opsForValue().set(key, o);
    }
}
