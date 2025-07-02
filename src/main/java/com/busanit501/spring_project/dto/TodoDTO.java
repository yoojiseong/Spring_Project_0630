package com.busanit501.spring_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    private Long tno;
    @NotEmpty//유효성 체크
    private String title;

    @Future//유효성 체크
    private LocalDate dueDate;
    private boolean finished;

    @NotEmpty //유효성 체크
    private String writer;

}
