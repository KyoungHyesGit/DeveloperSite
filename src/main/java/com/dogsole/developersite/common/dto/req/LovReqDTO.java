package com.dogsole.developersite.common.dto.req;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LovReqDTO {
    private String kind;
    private String label;
    private String value;
    private String state;
}
