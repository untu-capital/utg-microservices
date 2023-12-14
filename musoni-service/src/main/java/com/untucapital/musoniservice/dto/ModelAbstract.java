package com.untucapital.musoniservice.dto;

import lombok.*;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Embeddable
public class ModelAbstract implements Serializable {
    private Integer id;
    private String code;
    private String value;
}
