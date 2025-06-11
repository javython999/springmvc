package com.errday.servlet.annotationconverter;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MyModel {

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal price;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

}
