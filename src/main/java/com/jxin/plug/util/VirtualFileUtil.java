package com.jxin.plug.util;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * 虚拟文件工具类
 * @author 素律
 * @since 2021/8/2 10:56 上午
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class VirtualFileUtil {
    @NotNull
    public static String getBasePackage(AnActionEvent e, VirtualFile virtualDir) {
        final Module module = ModuleUtilCore.findModuleForFile(virtualDir, e.getProject());
        final String moduleRootPath = ModuleRootManager.getInstance(module).getContentRoots()[0].getPath();
        final String rootPath = StringUtils.substringAfter(virtualDir.getPath(), moduleRootPath);
        final String path = rootPath.replace("/src/main/java/", "").replace("/src/test/java/", "");
        return path.replace("/", ".");
    }
}
