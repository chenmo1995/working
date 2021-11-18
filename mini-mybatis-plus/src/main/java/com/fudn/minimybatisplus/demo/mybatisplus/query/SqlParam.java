package com.fudn.minimybatisplus.demo.mybatisplus.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bravo1988
 * @date 2021-05-29 19:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlParam {
    private String columnName;
    private Object value;
}
