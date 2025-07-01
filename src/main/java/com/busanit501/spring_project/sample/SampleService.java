package com.busanit501.spring_project.sample;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@ToString
//@RequiredArgsConstructor
public class SampleService {

    //필드 주입
    //루트 컨텍스트의 파일 위치를 인식하지 못해서 컴파일 오류 뜨는거임
//    @Autowired
//    private SampleDAO sampleDAO;

    //방법 2
    //생성자 주입 방식 -> 롬북 이용
    //해결책2,
    // 지정한 라벨을 표기, 선택하면 됨(event, normal)
    // 중복 사용이 안돼서 따로 분리 해서 설정
//    @Qualifier("normal")
    private final SampleDAO sampleDAO;
    // 따로 재정의 해서 적용하기
    public SampleService(@Qualifier("normal") SampleDAO sampleDAO){
        this.sampleDAO = sampleDAO;
    }
}
