package com.jxin.plug.group;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.jxin.plug.action.LoadLevelTmpAction;
import com.jxin.plug.core.level.repository.INodeRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 加载分层模板分组
 * @author Jxin
 * @since 2021/7/28 8:34 下午
 */
public class LoadLevelTmpGroup extends ActionGroup {
    private final INodeRepository nodeRepository;

    public LoadLevelTmpGroup() {
        this.nodeRepository = ServiceManager.getService(INodeRepository.class);
    }

    @NotNull
    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent anActionEvent) {
        if (anActionEvent == null) {
            return AnAction.EMPTY_ARRAY;
        }
        final Project project = PlatformDataKeys.PROJECT.getData(anActionEvent.getDataContext());
        if (project == null) {
            return AnAction.EMPTY_ARRAY;
        }
        return nodeRepository.allTmpKey()
                             .stream()
                             .map(this::getOrCreateAction)
                             .toArray(AnAction[]::new);
    }

    private AnAction getOrCreateAction(String key) {
        final String actionId = "TmpPorter.Menu.LoadLevelTmpGroup.Action." + key;
        final AnAction action = ActionManager.getInstance().getAction(actionId);
        if(action != null){
            return action;
        }
        return createAction(actionId, key);
    }
    private AnAction createAction(String actionId, String key) {
        final LoadLevelTmpAction action = new LoadLevelTmpAction(key);
        ActionManager.getInstance().registerAction(actionId, action);
        return action;
    }
}
