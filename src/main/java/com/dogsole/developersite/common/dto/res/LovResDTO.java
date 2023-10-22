package com.dogsole.developersite.common.dto.res;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LovResDTO {
    private Long id;
    private String kind;
    private String label;
    private String value;
    private String state;
}
