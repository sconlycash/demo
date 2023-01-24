package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class DemoApplicationTests {
	@Resource
	private DataSource dataSource;

	@Test
	void contextLoads() {
	}
  @Test
	void getConnection() throws SQLException {
	  System.out.println(dataSource.getConnection());
  }
}
