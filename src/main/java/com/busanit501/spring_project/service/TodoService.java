package com.busanit501.spring_project.service;

import com.busanit501.spring_project.dto.TodoDTO;

import java.util.List;

//인터페이스 명 : TodoService
// 구현 클래스 명 : TodoServiceImpl
public interface TodoService {
    void register(TodoDTO todoDTO);
    List<TodoDTO> getAll();
    TodoDTO selectByTno(Long tno);
    void updateByTno(TodoDTO todoDTO);
    void deleteByTno(Long tno);
}
