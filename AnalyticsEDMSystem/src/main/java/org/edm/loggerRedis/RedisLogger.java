package org.edm.loggerRedis;

import org.edm.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisLogger {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisLogger(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public void saveString(String key, String values) {
        int timeout = 2;
        redisTemplate.opsForValue().set(key,values);
        redisTemplate.expire(key,timeout, TimeUnit.DAYS);
    }
    public void saveString(String key, String values,int timeoutSec) {
        redisTemplate.opsForValue().set(key,values);
        redisTemplate.expire(key,timeoutSec, TimeUnit.SECONDS);
    }
    public void saveList(String key, List<Object> values) {
        ListOperations<String, Object> listOps = redisTemplate.opsForList();
        listOps.leftPushAll(key, values); // Добавление элементов в начало списка
    }

    public List<Object> getList(String key) {
        ListOperations<String, Object> listOps = redisTemplate.opsForList();
        return listOps.range(key, 0, -1); // Получение всех элементов списка
    }

}
