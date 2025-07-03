package service;

import com.busanit501.spring_project.dto.PageRequestDTO;
import com.busanit501.spring_project.dto.PageResponseDTO;
import com.busanit501.spring_project.dto.TodoDTO;
import com.busanit501.spring_project.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTests {

    @Autowired(required = false)
    private TodoService todoService;

    @Test
    public void testInsert(){
        //화면에서 전달 받은 더미 데이터 작성, TodoDTO
        TodoDTO todoDTO = TodoDTO.builder()
                .title("서비스 단위테스트 스프링 버전")
                .dueDate(LocalDate.now())
                .writer("유지성, 서비스 단위 테스트")
                .build();

        todoService.register(todoDTO);
    }

//    @Test
//    public void testGetAll(){
//        List<TodoDTO> dtoList = todoService.getAll();
//        dtoList.forEach(vo->log.info(vo));
//
//    }

    @Test
    public void testGetByTno(){
        TodoDTO todoDTO = todoService.selectByTno(7L);
        log.info(todoDTO);
    }

    @Test
    public void testDeleteByTno(){
        todoService.remove(4L);
    }

    @Test
    public void testUpdateByTno(){
        TodoDTO todoDTO = todoService.selectByTno(6L);
        todoDTO.setTitle("수정 테스트, 서비스에서 진행");
        todoService.modify(todoDTO);
    }

    @Test
    public void testPaging(){
        //더미 데이터 필요, 화면에서 전달받은 페이징 정보를 담은 PageRequestDTO가 필요함
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);
        log.info("서비스 단위테스트중 페이징 처리된 전체 조회 response");
        responseDTO.getDtoList().stream().forEach(dto->log.info(dto));
    }
}
