package com.codeloon.ems.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransportCostBean {

    private Long id;

    private String district;

    private Double deliveryFee;

    private LocalDateTime createdAt;
}

