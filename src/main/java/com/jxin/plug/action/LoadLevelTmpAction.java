package com.jxin.plug.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.jxin.plug.core.level.handle.CreateDirHandle;
import com.jxin.plug.core.level.repository.INodeRepository;
import com.jxin.plug.util.RunTimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * 加载分层模板
 * @author Jxin
 * @since 2021/7/28 9:13 下午
 */
public class LoadLevelTmpAction extends AnAction {
    private final String key;
    private final INodeRepository nodeRepository;

    public LoadLevelTmpAction(String key) {
        this.key = key;
        this.nodeRepository = ServiceManager.getService(INodeRepository.class);
        getTemplatePresentation().setDescription("");
        getTemplatePresentation().setText(key, false);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if(RunTimeUtil.isDumb(e)){
            return;
        }

        final var virtualDir = getDirVirtualFile(e);
        final var basePackage = getBasePackage(e, virtualDir);
        final var node = nodeRepository.get(key);
        final var createDirHandle = new CreateDirHandle();
        createDirHandle.setBasePackage(basePackage);
        createDirHandle.setAbsolutePath(virtualDir.getPath());
        createDirHandle.setRootNode(node);

        createDirHandle.createTmpDir();

    }
    @NotNull
    private String getBasePackage(AnActionEvent e, VirtualFile virtualDir) {
        final var module = ModuleUtilCore.findModuleForFile(virtualDir, e.getProject());
        final var moduleRootPath = ModuleRootManager.getInstance(module).getContentRoots()[0].getPath();
        return StringUtils.substringAfter(virtualDir.getPath(), moduleRootPath + "/src/main/java/")
                .replace("/", ".");
    }

    /**
     * 获取光标所在的文件夹
     * @param  e 活动事件
     * @return 光标所在的文件夹
     */
    private VirtualFile getDirVirtualFile(AnActionEvent e) {
        final var virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE);
        if (!virtualFile.isDirectory()) {
            return virtualFile.getParent();
        }
        return virtualFile;
    }
}
