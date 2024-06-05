package com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRSBasic {
    private Long id;
    private String nameCourse;
    private String description;
    private UserRSBasic intructorId;
    private MessageRSBasic message;
}
