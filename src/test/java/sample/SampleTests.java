package sample;

import com.busanit501.spring_project.sample.SampleService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class SampleTests {

    @Autowired
    private SampleService sampleService;
    //    root-context.xml 설정 파일에 dataSource이름의 객체 안에 포함 되어있고
    // hikariConfig 객체를 포함하고 있음
    // 서버 시작시 자동으로 객체를 생성 해줌
    @Autowired
    private DataSource dataSource;

    @Test
    public void testSampleService() {
        log.info("testSampleService======의존성 주입 테스트1");
        log.info("sampleService 의 인스턴스 조회 : " + sampleService);
        Assertions.assertNotNull(sampleService);
    }

    //디비 연결 테스트
    @Test
    public void testConnection () throws Exception{
        Connection connection = dataSource.getConnection();
        log.info("connection의 유무 : "+connection);
        Assertions.assertNotNull(connection);
        connection.close();
    }


}
