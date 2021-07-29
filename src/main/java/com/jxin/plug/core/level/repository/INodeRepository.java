package com.jxin.plug.core.level.repository;

import com.jxin.plug.core.level.mode.Node;

import java.util.Set;

/**
 * 节点仓储层 接口
 * @author Jxin
 * @since 2021/7/28 8:55 下午
 */

public interface INodeRepository{
    /**
     * 获取所有模板名称
     * @return 所有模板名称
     */
    Set<String> allTmpKey();

    /**
     * 获取模板
     * @param  key 模板名称
     * @return 模板
     */
    Node get(String key);

    /**
     * 删除模板
     * @param key 模板名称
     */
    void remove(String key);

    /**
     * 添加模板
     * @param key  模板名称
     * @param node 模板
     */
    void add(String key, Node node);
}
