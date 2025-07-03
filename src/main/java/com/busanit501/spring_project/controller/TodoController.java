package com.busanit501.spring_project.controller;

import com.busanit501.spring_project.dto.PageRequestDTO;
import com.busanit501.spring_project.dto.TodoDTO;
import com.busanit501.spring_project.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
// 대표 url,/todo,
// 밑에 메서드 하위  url 주소를 지정함.
// 최종 url, /todo/지정한 주소
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    // 최종 url : /todo/list
    @RequestMapping("/list")
    // void 라고하면, 메서드 명 : list
    // /WEB-INF/views/todo/list.jsp , 가리킴.
    // 자동 연결, 뷰 리졸버라는 친구의 업무.
    // 웹 화면에서 url : http://localhost:8080/todo/list?page=20&size=10
    // page,size정보를 자동 형변환 해준다.
    public void list2(@Valid PageRequestDTO pageRequestDTO,
                      BindingResult bindingResult,
                      Model model){
        log.info("TodoController에서 작업, list 호출 ");

        //페이징 전
//        List<TodoDTO> dtoList = todoService.getAll();
//        dtoList.forEach(vo -> log.info(vo));
//        // 서비스로 부터 외주 맡겨서, 디비 정보를 받아와서, 화면에 전달, 탑재하기.
//        model.addAttribute("dtoList", todoService.getAll());

        //페이징 후
        // 유효성 체크.
        if(bindingResult.hasErrors()){
            // size 최소 10, 최대 100 요청1000, 잘못된 요청 -> 기본으로 10으로 변경하기
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    }

    // 최종 url : /todo/register
    // 메소드 : get 
    // void 라고하면, 메서드 명 : register
    // /WEB-INF/views/todo/register.jsp , 가리킴.
    // 자동 연결, 뷰 리졸버라는 친구의 업무.
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register(){
        log.info("TodoController에서 작업, register 호출 ");
    }
    
    // 로직처리. 
    // 최종 url : /todo/register , 
    // 메소드 : post
    // 화면에서, TodoDTO 형식의 데이터를 전달을 받으면,
    // 각각 받는게 아니라, TodoDTO 모델 클래스로 한번에 받기 예시.

    //@Valid TodoDTO todoDTO : 유효성 검사 적용
    //BindingResult bindingResult, : 유효성 검사에 통과 못한 이유(원인)이 담겨져있음
    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        log.info("TodoController에서 작업, register , post , 로직처리");
        log.info("todoDTO:"+todoDTO);
        // 만약 유효성 체크를 통과하지 못한다면,
        //bindingResult 여기에 무언가 담겨져 있다.
        if(bindingResult.hasErrors()){
            log.info("TodoController에서 작업, register, post 에서 유효성 오류가 떴음");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        //디비에 반영하는 코드
        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping({"/read","/modify"})
    public void read(Long tno, Model model){
        TodoDTO todoDTO = todoService.selectByTno(tno);
        log.info(todoDTO);
        model.addAttribute("dto", todoDTO);


    }

    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes){
        log.info("삭제 작업 중..");
        log.info("tno : "+tno);
        todoService.remove(tno);
        return "redirect:/todo/list";
    }

    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            log.info("수정 적용 부분에서, 유효성 통과 못했음");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            // 현재 수정 화면에서, 수정할 내용을 입력 후, 유효성 체크 통과를 못했다면,
            // 다시 수정 화면으로 이동해야함,
            // 이떄 어떤 tno에서 작업하는지 알려줘야함
            // 이렇게 되면 쿼리 스트림으로 ?tno=21, 이렇게 화면에 전달 됨
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            // 최종 rul : /todo/modify?tno=21
            return "redirect:/todo/modify";
        }
        log.info("수정 로직 처리 post 작업중 넘어온 데이터 확인 : "+ todoDTO);
        todoService.modify(todoDTO);
        //PRG 패턴,
        return "redirect:/todo/list";
    }

}
