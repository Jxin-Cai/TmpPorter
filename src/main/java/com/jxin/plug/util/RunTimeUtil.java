package com.jxin.plug.util;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * 运行期工具
 * @author Jxin
 * @since 2021/7/29 7:58 下午
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RunTimeUtil {

    public static boolean isDumb(AnActionEvent e){
        return  Optional.of(e)
                        .map(AnActionEvent::getProject)
                        .map(DumbService::getInstance)
                        .map(DumbService::isDumb)
                        .orElse(true);
    }
}
