package com.trucking.entity;

import com.trucking.entity.enums.FailState;
import com.trucking.entity.enums.FailType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String description;
    @Enumerated(EnumType.STRING)
    private FailType type;
    @Enumerated(EnumType.STRING)
    private FailState state;

}
