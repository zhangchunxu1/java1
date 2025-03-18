package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserInterface userInterface;

    @Autowired
    private WebsiteRepository websiteRepository; // 添加一个 JPA 仓库，用于访问数据库

    @GetMapping("/user")
    public String getUser(@RequestParam String userId, @RequestParam String additionalInfo) {
        return userInterface.getUserInfo(userId, additionalInfo);
    }

    @GetMapping("/website")
    public String getWebsite(@RequestParam Integer id) {  // 使用 Integer
        System.out.println("Received ID: " + id);
        Optional<Website> website = websiteRepository.findById(id);  // 修正拼写错误
        System.out.println("Database Query Result: " + website);
        return website.map(w -> "Name: " + w.getName() + 
                              ", URL: " + w.getUrl() 
                            )
                      .orElse("Website not found");
    }
    
    
    
    
    
}
