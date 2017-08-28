package com.rabbitmq.service;

import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.util.SafeEncoder;

import java.util.*;

public class RedisClient {
    public  static BinaryClient.LIST_POSITION LIST_POSITION;

    private Jedis jedis;//非切片额客户端连接
    private JedisPool jedisPool;//非切片连接池
    private ShardedJedis shardedJedis;//切片额客户端连接
    private ShardedJedisPool shardedJedisPool;//切片连接池
    public RedisClient()
    {
        initialPool();
        initialShardedPool();
        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();
    }

    /**
     * 初始化非切片池
     */
    private void initialPool()
    {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(200);
        config.setMaxIdle(50);
        config.setMaxWaitMillis(10000l);
        config.setTestOnBorrow(false);

        jedisPool = new JedisPool(config,"192.168.2.129",6379);
    }

    /**
     * 初始化切片池
     */
    private void initialShardedPool()
    {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("192.168.2.129", 6379, "master"));

        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }
    public void show() {
        ListOperate();
        jedisPool.returnResource(jedis);
        shardedJedisPool.returnResource(shardedJedis);
    }
    public String ListOperate() {
        String funcName="";
        //System.out.println("======================list==========================");
        // 清空数据
        jedis.flushDB();
        //System.out.println("=============增=============");
        long startTimeLpush=System.currentTimeMillis();
        shardedJedis.lpush("stringlists", "vector");
        shardedJedis.lpush("stringlists", "ArrayList");
        shardedJedis.lpush("stringlists", "vector");
        shardedJedis.lpush("stringlists", "vector");
        shardedJedis.lpush("stringlists", "LinkedList");
        shardedJedis.lpush("stringlists", "MapList");
        shardedJedis.lpush("stringlists", "SerialList");
        shardedJedis.lpush("stringlists", "HashList");
        long endTimeLpush=System.currentTimeMillis();
        long durationLpush=endTimeLpush-startTimeLpush;
        funcName=funcName+"lpush,lpush8个值的执行时间："+durationLpush+"</br>";
        long startTimeRpush=System.currentTimeMillis();
        shardedJedis.rpush("numberlists", "3");
        shardedJedis.rpush("numberlists", "1");
        shardedJedis.rpush("numberlists", "5");
        shardedJedis.rpush("numberlists", "2");
        long endTimeRpush=System.currentTimeMillis();
        long durationRpush=endTimeRpush-startTimeRpush;
        funcName=funcName+"rpush,rpush4个值的执行时间："+durationRpush+"</br>";
        long startTimeLrange=System.currentTimeMillis();
        shardedJedis.lrange("stringlists", 0, -1);
        long endTimeLrange=System.currentTimeMillis();
        long durationLrange=endTimeLrange-startTimeLrange;
        funcName=funcName+"lrange,lrange 1个key8个value的时间："+durationLrange+"</br>";
        shardedJedis.lrange("numberlists", 0, -1);
        //System.out.println("=============删=============");
        // 删除列表指定的值 ，第二个参数为删除的个数（有重复时），后add进去的值先被删，类似于出栈
        long startTimeLremove=System.currentTimeMillis();
        shardedJedis.lrem("stringlists", 2, "vector");
        long endTimeLremove=System.currentTimeMillis();
        long durationLremove=endTimeLremove-startTimeLremove;
        funcName=funcName+"lrem,lrem一个值的时间："+durationLremove+"</br>";
        //shardedJedis.lrange("stringlists", 0, -1);
        // 删除区间以外的数据
        long startTimeLtrim=System.currentTimeMillis();
       shardedJedis.ltrim("stringlists", 0, 3);
        long endTimeLtrim=System.currentTimeMillis();
        long durationLtrim=endTimeLtrim-startTimeLtrim;
        funcName=funcName+"ltrim,ltrim的执行时间："+durationLremove+"</br>";
        //shardedJedis.lrange("stringlists", 0, -1);
        // 列表元素出栈
        long startTimeLpop=System.currentTimeMillis();
       shardedJedis.lpop("stringlists");
        long endTimeLpop=System.currentTimeMillis();
        long durationLpop=endTimeLpop-startTimeLpop;
        funcName=funcName+"lpop,lpop的执行时间："+durationLpop+"</br>";
        //shardedJedis.lrange("stringlists", 0, -1);

        //System.out.println("=============改=============");
        // 修改列表中指定下标的值
        long startTimeLset=System.currentTimeMillis();
        shardedJedis.lset("stringlists", 0, "hello list!");
        long endTimeLset=System.currentTimeMillis();
        long durationLset=endTimeLset-startTimeLset;
        funcName=funcName+"lset,lset的执行时间："+durationLset+"</br>";
        //shardedJedis.lrange("stringlists", 0, -1);
        //System.out.println("=============查=============");
        // 数组长度
        long startTimeLlen=System.currentTimeMillis();
        shardedJedis.llen("stringlists");
        long endTimeLlen=System.currentTimeMillis();
        long durationLlen=endTimeLset-startTimeLset;
        funcName=funcName+"llen,llen的执行时间："+durationLlen+"</br>";
        shardedJedis.llen("numberlists");
        // 排序
          /*
           * list中存字符串时必须指定参数为alpha，如果不使用SortingParams，而是直接使用sort("list")，
           * 会出现"ERR One or more scores can't be converted into double"
           */
        SortingParams sortingParameters = new SortingParams();
        sortingParameters.alpha();
        sortingParameters.limit(0, 3);
       shardedJedis.sort("stringlists",sortingParameters);
        shardedJedis.sort("numberlists");
        // 子串：  start为元素下标，end也为元素下标；-1代表倒数一个元素，-2代表倒数第二个元素
        //shardedJedis.lrange("stringlists", 1, -1);
        // 获取列表指定下标的值
        long startTimeLindex=System.currentTimeMillis();
        shardedJedis.lindex("stringlists", 2);
        long endTimeLindex=System.currentTimeMillis();
        long durationLindex=endTimeLset-startTimeLset;
        funcName=funcName+"lindex,lindex的执行时间："+durationLindex+"</br>";
        return  funcName;
    }

}
