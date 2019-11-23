package com.leyou.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("user")
public class UserController {

    @PostMapping("login")
    public ResponseEntity<String> login() {
        String res = "{\"code\":20000,\"data\":{\"token\":\"admin-token\"}}";
        if (null == res || res.length() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("info")
    public ResponseEntity<String> info() {
        String res = "{\"code\":20000,\"data\":{\"roles\":[\"admin\"],\"introduction\":\"I am a super administrator\",\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\",\"name\":\"Super Admin\"}}";
        if (null == res || res.length() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout() {
        String res = "{\"code\":20000,\"data\": \"success\"}";
        if (null == res || res.length() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(res);
    }
}
