package com.jxin.plug.core.level.repository.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模板数据模型
 * @author Jxin
 * @since 2021/7/29 5:39 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TmpDo {
    /**模板名*/
    private String name;
    /**模板内容 json*/
    private String tmpJson;
}
