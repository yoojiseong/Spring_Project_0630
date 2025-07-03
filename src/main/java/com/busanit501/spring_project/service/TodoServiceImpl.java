package com.busanit501.spring_project.service;

import com.busanit501.spring_project.domain.TodoVO;
import com.busanit501.spring_project.dto.PageRequestDTO;
import com.busanit501.spring_project.dto.PageResponseDTO;
import com.busanit501.spring_project.dto.TodoDTO;
import com.busanit501.spring_project.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor //final필드를 생성자 주입
public class TodoServiceImpl implements TodoService {
    //의존성 주입, 외주 주기. 다른 객체 불러오기(포함하기), 의지(의존)하기
    private final TodoMapper todoMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(TodoDTO todoDTO){
        log.info("컨트롤러부터 넘어온 데이터 확인 : " +todoDTO);
        //타입 변경 DTO -> VO
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        //실제 작업, 외주 주기, DAO부탁 데이터 입력 해줘
        log.info("변환 된 데이터 확인 todovO : " + todoVO);
        todoMapper.insert(todoVO);
    }


    //페이징 전 전체 조회
//    @Override
//    public List<TodoDTO> getAll(){
//        // 디비로 부터 전달 받은TodoVo -> TodoDTO로 변환 작업
//        // 병렬 처리로 진행하기.
//        List<TodoDTO> dtoList = todoMapper.selectAll().stream()
//                .map(vo->modelMapper.map(vo, TodoDTO.class)).
//                collect(Collectors.toList());
//        return dtoList;
//    }

    //페이징 후 전체 조회
    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        // 터 페이징 처리 된 데이터 목록(TodoVO -> TodoDTO : TodoMapper)
        // 2) 디비로 부터 전달준비물
        //        // 1) 디비로 부터 받은 전체 갯수
        // 3) pageRequestDTO(페이징 정보, 사이즈 정보, skip정보)

        // 1) 디비로 부터 받은 타입 vo->dto로 변환이 필요함
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        List<TodoDTO> dtoList = voList.stream().map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        // 2)
        int total = todoMapper.getCount(pageRequestDTO);
        //준비물, 박스에 담아서 서비스 -> 컨트롤러에 전달
        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;
    }

    @Override
    public TodoDTO selectByTno(Long tno){
        TodoVO todoVO = todoMapper.selectByTno(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;
    }

    @Override
    public void modify(TodoDTO todoDTO){
        TodoVO todoVo = modelMapper.map(todoDTO, TodoVO.class);
        todoMapper.modify(todoVo);
    }

    @Override
    public void remove(Long tno) {
        todoMapper.delete(tno);
    }
}
