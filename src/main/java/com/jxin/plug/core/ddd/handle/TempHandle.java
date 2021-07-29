package com.jxin.plug.core.ddd.handle;

import cn.hutool.core.io.FileUtil;
import com.jxin.plug.core.ddd.consts.NodeEnum;
import com.jxin.plug.core.ddd.mode.Node;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 模板执行器
 * @author Jxin
 * @since 2021/7/28 4:42 下午
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TempHandle {

    public static Node createTemp(String path, String basePackage){
        final var tempRoot = new File(path);
        final Node ret = new Node();
        ret.setName(tempRoot.getName());
        ret.setBasePackage(basePackage);
        ret.setType(NodeEnum.ROOT.name());
        ret.setAbsolutePath(tempRoot.getAbsolutePath());
        final File[] files = tempRoot.listFiles();
        ret.setChildNode(getChildNode(files));
        return ret;
    }

    private static Node getNode(File file){
        final boolean isDir = FileUtil.isDirectory(file);
        if(isDir){
            return getDirNode(file);
        }
        return getFileNode(file);
    }
    private static Node getDirNode(File file){
        final Node ret = new Node();
        ret.setName(file.getName());
        ret.setType(NodeEnum.DIR.name());
        ret.setAbsolutePath(file.getAbsolutePath());
        final File[] files = file.listFiles();
        ret.setChildNode(getChildNode(files));
        return ret;
    }
    private static List<Node> getChildNode(File[] files){
        if(ArrayUtils.isEmpty(files)){
            return Collections.emptyList();
        }
        return Arrays.stream(files)
                     .map(TempHandle::getNode)
                     .collect(Collectors.toList());
    }
    private static Node getFileNode(File file){
        final Node ret = new Node();
        ret.setName(file.getName());
        ret.setType(NodeEnum.FILE.name());
        ret.setAbsolutePath(file.getAbsolutePath());
        ret.setContext(FileUtil.readUtf8String(file));
        return ret;
    }
}
