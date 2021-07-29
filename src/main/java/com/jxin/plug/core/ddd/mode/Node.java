package com.jxin.plug.core.ddd.mode;

import com.alibaba.fastjson.JSON;
import com.jxin.plug.core.ddd.consts.NodeEnum;
import lombok.Data;

import java.util.List;

/**
 * 节点信息
 * @author Jxin
 * @since 2021/7/28 4:34 下午
 */
@Data
public class Node {
    /**节点名称*/
    private String name;
    /**节点全路径*/
    private String absolutePath;
    /**包路径*/
    private String basePackage;
    /**
     * 节点类型
     * {@link NodeEnum}
     */
    private String type;
    /**文件内容*/
    private String context;
    /**子节点列表*/
    private List<Node> childNode;

    public boolean isDir(){
        return NodeEnum.DIR.name().equals(type);
    }
    public String dump(){
        return JSON.toJSONString(this);
    }
    public static Node of(String json){
        return JSON.parseObject(json, Node.class);
    }
}
