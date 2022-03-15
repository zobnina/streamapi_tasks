package com.tasks.streamapi.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Order {

    private String customerId;
    private LocalDate orderDate;
    private BigDecimal orderTotal;
}
