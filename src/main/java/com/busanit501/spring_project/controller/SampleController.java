package com.busanit501.spring_project.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@Log4j2
public class SampleController {
    // 1)화면 제공, 2) 로직처리
    @GetMapping("/hello")
    public void hello() {
        log.info("hello.......");
    }

    //단순 파라미터 자동수집
    @GetMapping("ex1")
    // 기본 자료형으로 정의
    // 화면에서 쿼리스트링 형식(get방식)으로 데이터를 전달
    // http://localhost:8080/ex1?name=ujs&age=20
    public void ex1(String name, int age) {
        log.info("SampleController에서 작업중.");
        log.info("name: " + name+", age: " + age);
    }

    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name", defaultValue = "사용자") String name,
                    @RequestParam(name = "age", defaultValue = "24") int age){
        log.info("SampleController에서 작업중. ex2, 값이 없는 경우 기본 값 이용");
        log.info("name: " + name+", age: " + age);
    }

    // 날짜 포맷터 인 경우
    // http://localhost:8080/ex3?dueDate=2025-07-01
    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate){
        log.info("SampleController에서 작업중. ex3, 날짜 포맷터 형식인 경우");
        log.info("dueDate: " + dueDate);
    }

}
