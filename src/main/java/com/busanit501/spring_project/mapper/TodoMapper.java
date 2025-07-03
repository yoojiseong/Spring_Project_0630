package com.busanit501.spring_project.mapper;

import com.busanit501.spring_project.domain.TodoVO;
import com.busanit501.spring_project.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {
    //테스트 메소드 디비로부터 시간 데이터 가져오기
    String getTime();

    //todo등록
    void insert(TodoVO todoVO);

    //전체 조회
    List<TodoVO> selectAll();

    //하나 조회, 상세보기
    TodoVO selectByTno(Long tno);

    void modify(TodoVO todoVO);

    void delete(Long tno);

    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    //페이징 준비물 2 전체 갯수 파악
    int getCount(PageRequestDTO pageRequestDTO);
}
