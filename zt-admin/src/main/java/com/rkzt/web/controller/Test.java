package com.rkzt.web.controller;


import com.rkzt.web.domain.TT;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/t")
public class Test {
    @GetMapping("Test")
    public String tt(){

        TT tt = new TT();
        return "success";
    }

}
