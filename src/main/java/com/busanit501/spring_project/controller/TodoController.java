package com.busanit501.spring_project.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
// 대표 url./todo
// 밑에 메서드에서 하위 url주소를 지정함
// 최종 url, /todo/지정한 주소
@RequestMapping("/todo")
public class TodoController {

    //최종 url : /todo/list
    @RequestMapping("/list")
    //void라고 하면 메서드명 : list
    // /WEB-INF/views/list.jsp를 카리킹
    // 자동 연결
    public void list(){
        log.info("TodoController에서 작업중, list 호출");
    }
}
