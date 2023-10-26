package com.dogsole.developersite.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Setter
@Getter
@ToString
@DynamicUpdate
@Table(name = "lov")
public class LovEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lov_id")
    private Long id;
    @Column(name = "kind")
    private String kind;
    @Column(name = "label")
    private String label;
    @Column(name = "value")
    private String value;
    @Column(name = "state")
    private String state;
}
