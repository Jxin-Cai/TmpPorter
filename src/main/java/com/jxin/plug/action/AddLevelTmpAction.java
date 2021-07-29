package com.jxin.plug.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.jxin.plug.core.ddd.handle.TempHandle;
import com.jxin.plug.core.ddd.repository.INodeRepository;
import com.jxin.plug.core.ddd.repository.NodeRepository;
import com.jxin.plug.util.RunTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * 添加分层模板
 * @author Jxin
 * @since 2021/7/28 4:04 下午
 */
@Slf4j
public class AddLevelTmpAction extends AnAction {
    private final INodeRepository nodeRepository;
    public AddLevelTmpAction() {
        this.nodeRepository = ServiceManager.getService(INodeRepository.class);
    }
    @Override
    public void actionPerformed(AnActionEvent e) {
        if(RunTimeUtil.isDumb(e)){
            return;
        }

        final var virtualDir = getDirVirtualFile(e);
        final var basePackage = getBasePackage(e, virtualDir);
        final var node = TempHandle.createTemp(virtualDir.getPath(), basePackage);
        nodeRepository.add(node.getName(), node);
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
    @NotNull
    private String getBasePackage(AnActionEvent e, VirtualFile virtualDir) {
        final var module = ModuleUtilCore.findModuleForFile(virtualDir, e.getProject());
        final var moduleRootPath = ModuleRootManager.getInstance(module).getContentRoots()[0].getPath();
        return StringUtils.substringAfter(virtualDir.getPath(), moduleRootPath + "/src/main/java/")
                .replace("/", ".");
    }
}
