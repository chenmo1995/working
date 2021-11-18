package com.fudn.minimybatisplus.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fdn
 * @since 2021-11-16 23:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlParam {
    private String columnName;
    private Object value;
}
