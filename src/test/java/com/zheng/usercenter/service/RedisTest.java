package com.zheng.usercenter.service;

import com.zheng.usercenter.model.entity.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Author: Zheng Zhang
 * @Description
 * @Created 08/25/2023 - 15:53
 */
@SpringBootTest
public class RedisTest {
  
  @Resource
  private RedisTemplate redisTemplate;
  
  @Test
  void redisTest() {
    ValueOperations valueOperations = redisTemplate.opsForValue();
    valueOperations.set("testInt", 1);
    valueOperations.set("testDouble", 1.0);
    valueOperations.set("testString", "happy");
    User user = new User();
    user.setId(123L);
    user.setUserAccount("1234566");
    user.setUserName("Andy");
    valueOperations.set("testUser", user);
  
    Object testInt = valueOperations.get("testInt");
    Assertions.assertTrue(1 == (int) testInt);
    Object testDouble = valueOperations.get("testDouble");
    Assertions.assertTrue(1.0 == (double) testDouble);
    Object testString = valueOperations.get("testString");
    Assertions.assertTrue("happy".equals((String) testString));
    System.out.println(valueOperations.get("testUser"));
  }
}
