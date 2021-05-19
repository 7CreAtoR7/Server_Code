package com.example;
import java.util.List;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class UsersInfo {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/hi")
    public String hi() {
        return "Hi";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/list")
    public List<Users> list(){
        ArrayList<Users> ls=new ArrayList<Users>();
         try (Connection connection = dataSource.getConnection()) {
              Statement stmt = connection.createStatement();
              ResultSet rs = stmt.executeQuery("SELECT * FROM users");
              while (rs.next()) {
                ls.add(new Users(rs.getString("name"), rs.getString("score")));
              }
            } catch (Exception e) {
            }
        return ls;
    }
}

