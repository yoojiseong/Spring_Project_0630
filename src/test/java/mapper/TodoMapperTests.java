package mapper;

import com.busanit501.spring_project.domain.TodoVO;
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
        todoMapper.updateByTno(todoVO);
        TodoVO todoVO2 = todoMapper.selectByTno(7L);
        log.info(todoVO2);
    }

    @Test
    public void testDelete(){
        TodoVO todoVO = todoMapper.selectByTno(3L);
        log.info("당신이 고른 tno 정보" +todoVO);
        todoMapper.deleteByTno(3L);
    }
}
