package com.xxxx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xcdgg
 * @description
 * @date 2022/2/7 19:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {
    private Long total;

    private List<?> data;
}
