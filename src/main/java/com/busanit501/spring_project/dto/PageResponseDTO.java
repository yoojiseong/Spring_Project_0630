package com.busanit501.spring_project.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

// 서버 -> 웹 화면으로 , 페이징 정보를 전달함
// 1) 페이징 처리된 todo의 목록, 디비 조회한 내용
// 2) 전체 갯수
// 3) 기본 페이지 정보
// 4) 사이즈 정보
// 5) 시작 페이지
// 6) 끝 페이지
// 7) 이전 페이지 여부
// 8) 다음 페이지 여부
// <E>제네릭으로 표현한 이유, 1회용으로 사용하는게 아니라
// 나중에 다른 도메인으로도 사용 가능
// ex) 댓글, 상품, 회원, 등 목록화 할 수 있는 주제는 다 페이징을 해야함
// 매우 중요한 기술임
@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int total;

    private int start;
    private int end;

    private boolean prev;
    private boolean next;

    private List<E> dtoList;

    //빌더 패턴을 이용한 생성자 구성
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        // 5) 시작 페이지
        // 6) 끝 페이지
        // 7) 이전 페이지 여부
        // 8) 다음 페이지 여부
        this.end = (int)(Math.ceil(this.page/10.0))*10;
        //끝 페이지가 20 이면 시작 페이지는 11이여서
        this.start = this.end-9;
        // last는 전체 갯수가 딱 떨어지지 않을때 ex) 데이터가 160개면 페이지는 16까지 필요함
        // 1~10, 11~16 이 중 16을 구하는게 last임
        int last = (int)(Math.ceil((total/(double)size)));
        this.end = end > last ? last : end;
        this.prev = this.start>1;
        this.next = total > this.end * this.size;

    }

}
