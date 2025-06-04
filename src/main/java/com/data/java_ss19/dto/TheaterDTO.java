package com.data.java_ss19.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class TheaterDTO {
    private Long id;

    @NotBlank(message = "Tên rạp không được để trống")
    private String theaterName;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @Min(value = 1, message = "Số lượng phòng chiếu phải lớn hơn 0")
    private int numberScreenRoom;

    private boolean status = true;
}
