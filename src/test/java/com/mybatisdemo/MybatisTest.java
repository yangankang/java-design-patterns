package com.mybatisdemo;

import com.mybatisdemo.mapper.UserLoginMapper;
import com.mybatisdemo.model.UserLogin;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    @Test
    public void normal() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserLoginMapper mapper = session.getMapper(UserLoginMapper.class);
            List<UserLogin> userLogins = mapper.queryAll();
//            UserLogin userLogin = mapper.queryByName("un_1");
//            System.out.println(userLogin.getUserName());
        } finally {
            session.close();
        }
    }
}
