package com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonRSBasic {
    private Long id;
    private String title;
    private String description;
    private CourseRSBasic course_id;
}