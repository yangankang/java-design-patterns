package com.mybatisdemo;

import com.mybatisdemo.service.UserLoginServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@EnableTransactionManagement
class MybatisdemoApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        //template模板，拿来即用
        connection.close();
    }

    @Autowired
    UserLoginServices userLoginServices;

    @Test
    public void toTest() {
        userLoginServices.queryByName("abcd");
    }
}
