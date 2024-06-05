package com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse;

import java.time.LocalDateTime;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageRSBasic {
    private Long id;
    private String content;
    private LocalDateTime date;
    private UserRSBasic userReceiver;
    private UserRSBasic userSender;
    private CourseRSBasic course;
}
