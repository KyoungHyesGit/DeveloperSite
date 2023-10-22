package com.dogsole.developersite.vender.dto.res;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VenderResDTO {

    private Long id;
    private String email;
    private String name;
    private String passwd;

    private LocalDateTime createDt;
    private LocalDateTime updateDt;
}
