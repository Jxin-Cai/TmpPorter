package com.jxin.plug.core.level.handle;

import cn.hutool.core.io.FileUtil;
import com.jxin.plug.core.level.consts.NodeEnum;
import com.jxin.plug.core.level.mode.Node;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.Sets;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 模板执行器
 * @author Jxin
 * @since 2021/7/28 4:42 下午
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TempHandle {
    private static final Set<String> KEEP_FILE_NAME =
            Sets.newHashSet("package-info.java",
                            "pom.xml",
                            ".gitignore"
            );
    public static Node createTemp(String path, String basePackage){
        final var tempRoot = new File(path);
        final var ret = new Node();
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
        final var ret = new Node();
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
                     .filter(Objects::nonNull)
                     .collect(Collectors.toList());
    }
    private static Node getFileNode(File file){
        final String name = file.getName();
        if(!KEEP_FILE_NAME.contains(name)){
            return null;
        }
        final var ret = new Node();
        ret.setName(name);
        ret.setType(NodeEnum.FILE.name());
        ret.setAbsolutePath(file.getAbsolutePath());
        ret.setContext(FileUtil.readUtf8String(file));
        return ret;
    }
}
