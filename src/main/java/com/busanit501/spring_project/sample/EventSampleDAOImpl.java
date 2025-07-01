package com.busanit501.spring_project.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

// 구현체2
@Repository
// 해결책1
//@Primary
//해결책 2, 라벨달기
@Qualifier("event")
public class EventSampleDAOImpl implements SampleDAO{
}
