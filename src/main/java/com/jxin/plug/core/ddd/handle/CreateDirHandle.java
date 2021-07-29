package com.jxin.plug.core.ddd.handle;

import cn.hutool.core.io.FileUtil;
import com.jxin.plug.core.ddd.mode.Node;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

/**
 * 创建文件目录的执行器
 * @author Jxin
 * @since 2021/7/28 5:35 下午
 */
@Data
public class CreateDirHandle {
    /**包路径*/
    private String basePackage;
    /**绝对路径*/
    private String absolutePath;
    /**模板跟节点*/
    private Node rootNode;
    /**老的包路径*/
    private String oldBasePackage;
    /**老的全路径*/
    private String oldAbsolutePath;

    public void createTmpDir(){
        oldBasePackage = rootNode.getBasePackage();
        oldAbsolutePath = rootNode.getAbsolutePath();
        rootNode.getChildNode().forEach(this::create);
    }
    private void create(Node node){
        if (node.isDir()){
            createDir(node);
            return;
        }
        createFile(node);
    }
    private void createDir(Node node){
        final String name = node.getName();
        final String newAbsolutePath = getNewAbsolutePath(node);
        new File(newAbsolutePath, name);
        final List<Node> childNode = node.getChildNode();
        if(CollectionUtils.isEmpty(childNode)){
            return;
        }
        childNode.forEach(this::create);
    }
    public void createFile(Node node){
        final String newAbsolutePath = getNewAbsolutePath(node);
        final String context = getNewContext(node);
        FileUtil.appendUtf8String(context, newAbsolutePath);
    }

    @NotNull
    private String getNewContext(Node node) {
        return node.getContext()
                   .replace(oldBasePackage, basePackage);
    }

    @NotNull
    private String getNewAbsolutePath(Node node) {
        return node.getAbsolutePath()
                   .replace(oldAbsolutePath, absolutePath);
    }
}
