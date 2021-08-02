package com.jxin.plug.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.jxin.plug.core.level.handle.TempHandle;
import com.jxin.plug.core.level.mode.Node;
import com.jxin.plug.core.level.repository.INodeRepository;
import com.jxin.plug.util.RunTimeUtil;
import com.jxin.plug.util.VirtualFileUtil;
import lombok.extern.slf4j.Slf4j;

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

        final VirtualFile virtualDir = getDirVirtualFile(e);
        final String basePackage = VirtualFileUtil.getBasePackage(e, virtualDir);
        final Node node = TempHandle.createTemp(virtualDir.getPath(), basePackage);
        nodeRepository.add(node.getName(), node);
    }

    /**
     * 获取光标所在的文件夹
     * @param  e 活动事件
     * @return 光标所在的文件夹
     */
    private VirtualFile getDirVirtualFile(AnActionEvent e) {
        final VirtualFile virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE);
        if (!virtualFile.isDirectory()) {
            return virtualFile.getParent();
        }
        return virtualFile;
    }
}
