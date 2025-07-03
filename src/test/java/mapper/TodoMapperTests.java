package mapper;

import com.busanit501.spring_project.domain.TodoVO;
import com.busanit501.spring_project.dto.PageRequestDTO;
import com.busanit501.spring_project.mapper.TodoMapper;
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
public class TodoMapperTests {
    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testgetTime(){
        log.info("디비로부터 시간 정보 가져오기 확인 : " + todoMapper.getTime());

    }

    @Test
    public void testInsert(){
        TodoVO todoVO = TodoVO.builder()
                .title("오늘 점심 뭐먹지? 스프링 버전")
                .dueDate(LocalDate.now())
                .writer("유지성 스프링 버전")
                .build();
        todoMapper.insert(todoVO);
    }

    @Test
    public void testSelectAll(){
        List<TodoVO> voList = todoMapper.selectAll();
        voList.forEach(vo->log.info(vo));
    }

    @Test
    public void testSelectByTno(){
        TodoVO todoVO = todoMapper.selectByTno(7L);
        log.info(todoVO);
    }

    @Test
    public void testUpdate(){

        TodoVO todoVO = TodoVO.builder()
                .tno(7L)
                .title("집 가서 설거지 하기22")
                .dueDate(LocalDate.now())
                .writer("유지성 업데이트")
                .build();
        todoMapper.modify(todoVO);
        TodoVO todoVO2 = todoMapper.selectByTno(7L);
        log.info(todoVO2);
    }

    @Test
    public void testDelete(){
        TodoVO todoVO = todoMapper.selectByTno(3L);
        log.info("당신이 고른 tno 정보" +todoVO);
        todoMapper.delete(3L);
    }

    //페이징 처리가 된 전체 리스트
    @Test
    public void testSelectListwithPage() {
        // 준비물, 화면에서 전달받은 페이징 정보가 필요함
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2) //확인시 페이지 번호만 변경하면 됨
                .size(10)
                .build();
        List<TodoVO> todoVOList = todoMapper.selectList(pageRequestDTO);
        todoVOList.forEach(vo -> log.info(vo));
    }

    @Test
    public void testSelectCount(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2) //확인시 페이지 번호만 변경하면 됨
                .size(10)
                .build();
        int total = todoMapper.getCount(pageRequestDTO);
        log.info("전체 갯수 : "+total);
    }
}
