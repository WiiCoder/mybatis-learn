package com.wii.mybatis.binding;

import com.wii.mybatis.binding.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.util.HashMap;

public class ApiTest {

    private final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void testMapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
        HashMap<String, String> sqlSession = new HashMap<>();
        sqlSession.put("com.wii.mybatis.binding.dao.IUserDao.queryUserName", "执行查询：名称");
        sqlSession.put("com.wii.mybatis.binding.dao.IUserDao.queryUserAge", "执行查询：年龄");

        IUserDao userDao = factory.newInstance(sqlSession);

        String age = userDao.queryUserAge("101");
        logger.info("年龄：{}", age);
    }

    @Test
    public void testProxyClass() {
        IUserDao userDao = (IUserDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserDao.class}, (proxy, method, args) -> "你被代理了！");
        String result = userDao.queryUserName("10001");
        System.out.println("测试结果：" + result);
    }

}
