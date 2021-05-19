package com.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
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
                ls.add(new Users(rs.getString("name"), rs.getInt("score")));
              }
            } catch (Exception e) {
            }
        return ls;
    }
    
    @RequestMapping(method = RequestMethod.GET,value = "/add")
    public Boolean add(@RequestBody Users urs) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("insert into users (name,score) values(?,?)");
            stmt.setString(1, urs.name);
            stmt.setInt(2, urs.score);
          } catch (Exception e) {
          }
    	return false;
    	
    }
    
    
}

