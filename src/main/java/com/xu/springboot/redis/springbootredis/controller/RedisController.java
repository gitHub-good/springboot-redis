package com.xu.springboot.redis.springbootredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.*;

@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate = null;
    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    /**
     * Redis Hash数据类型的使用
     * @return
     */
    @RequestMapping("/map")
    public Map<String, Object> testStringAndMap(){
//        redisTemplate.opsForValue().set("int_key",1);
//        stringRedisTemplate.opsForValue().set("int_k","1");
        //increment增值运算
        stringRedisTemplate.opsForValue().increment("int_k",1);
        //获取底层Jedis连接
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.decr("int");
        Map<String, String> hash = new HashMap<>();
        hash.put("field1","value1");
        hash.put("field2","value2");
        stringRedisTemplate.opsForHash().putAll("hash",hash);
       //添加一个元素到hash
        stringRedisTemplate.opsForHash().put("hash","field3","value3");
        //BoundXXXOperations类 绑定散列操作的key,可以连续对同一个散列数据类型进行操作 stringRedisTemplate/redisTemplate . boundXXXOps("需要绑定的数据")
        BoundHashOperations hashOperations = stringRedisTemplate.boundHashOps("hash");
        hashOperations.delete("field1","field2");
        hashOperations.put("field4","value4");
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    @RequestMapping("/set")
    public Map<String, Object> testSet(){
        Integer[] integers = {1,2,3,4,5,6};
        redisTemplate.opsForSet().add("set01",integers);
        BoundSetOperations boundSetOperations = redisTemplate.boundSetOps("set01");
        boundSetOperations.add(1314);
        boundSetOperations.intersect(1);
//        boundSetOperations.diff(1);

        Map<String, Object> map = new HashMap<>();
        map.put("seccuss", true);
        return map;
    }
    /**
     * Redis ZSet有序集合的使用
     * @return
     */
    @RequestMapping("/zset")
    public Map<String, Object> testZset(){
        //
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();
        for (int i=1;i<=9;i++){
            double score = i*0.1;
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>("value"+i, score);
            typedTupleSet.add(typedTuple);
        }
        //添加一个ZSet有序集合到redis中（zset01）
        stringRedisTemplate.opsForZSet().add("zset01", typedTupleSet);
        //绑定该ZSet集合进行操作
        BoundZSetOperations<String, String> boundZSetOperations = stringRedisTemplate.boundZSetOps("zset01");
        boundZSetOperations.add("value10",0.26);
        Set<String> setRange = boundZSetOperations.range(1, 6);/*操作zset01有序集合查出范围在1到6的集合的key值*/
        System.out.println(setRange);
       //按分数排序获取有序集合
        Set<String> setScore = boundZSetOperations.rangeByScore(0.2,0.6);
        System.out.println(setScore);
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.gt("value3");/*大于value3*/
//        range.gte("value3");/*大于等于value3*/
//        range.lt("value8");/*小于value8*/
        range.lte("value8");/*小于等于value8*/

        Set<String> setLex = boundZSetOperations.rangeByLex(range);
        System.out.println(setLex);
//        boundZSetOperations.remove("value9","value2");
//        Double score = boundZSetOperations.score("value8");
//        Set<ZSetOperations.TypedTuple<String>> rangeSet = boundZSetOperations.rangeWithScores(1,6);
//        Set<ZSetOperations.TypedTuple<String>> scoreSet = boundZSetOperations.rangeByScoreWithScores(1,6);
//        Set<String> reverseSet = boundZSetOperations.reverseRange(2,8);

        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    /**
     * redis事务处理
     * @return
     */
    @RequestMapping("/Multi")
    public Map<String, Object> testMulti(){
        redisTemplate.opsForValue().set("key1", "value1");
//        List list = (List) redisTemplate.execute((RedisOperations op) ->{
//           op.watch("key1");/*监控key1*/
//           op.multi();/*redis开启事务*/
//           op.opsForValue().set("key2","value2");/*在redis事务开启过程中无法取到值，因为没有把值写入redis缓存中*/
//           Object value2 = op.opsForValue().get("key2");/*返回null*/
//            System.out.println(value2);
//           return op.exec();/*提交redis事务处理*/
//        });
//        System.out.println(list);

        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    /**
     * redis流水线
     * @return
     */
    @RequestMapping("/pipeLine")
    public Map<String, Object> testPipeLine(){
        Long start = System.currentTimeMillis();
//        List list = (List) redisTemplate.executePipelined((RedisOperations op) -> {
//            for (int i = 1; i < 100000; i++) {
//                op.opsForValue().set("pipeLine_" + i, "value_" + i);
//                String value = (String) op.opsForValue().get("pipeLine_" + i);
//                if (i == 100000) {
//                    System.out.println("命令只是进入队列，所有值为空【" + value + "】");
//                }
//            }
//            return null;
//        });
        Long end = System.currentTimeMillis();
        System.out.println("运行耗时："+(end - start)+"毫秒");

        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

}
