package com.jxin.plug.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ServiceManager;
import com.jxin.plug.core.ddd.repository.INodeRepository;
import com.jxin.plug.util.RunTimeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 添加分层模板
 * @author Jxin
 * @since 2021/7/28 4:04 下午
 */
@Slf4j
public class RemoveLevelTmpAction extends AnAction {
    private final String key;
    private final INodeRepository nodeRepository;

    public RemoveLevelTmpAction(String key) {
        this.key = key;
        this.nodeRepository = ServiceManager.getService(INodeRepository.class);
    }
    @Override
    public void actionPerformed(AnActionEvent e) {
        if(RunTimeUtil.isDumb(e)){
            return;
        }
        nodeRepository.remove(key);
    }
}
