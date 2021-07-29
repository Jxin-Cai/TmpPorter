package com.jxin.plug.core.level.repository;

import com.google.common.collect.Maps;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.jxin.plug.core.level.mode.Node;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 节点仓储层
 * @author Jxin
 * @since 2021/7/28 8:55 下午
 */
@State(name = "nodeRepository", storages = @Storage("level-tmp.xml"))
@EqualsAndHashCode
public class NodeRepository implements PersistentStateComponent<NodeRepository.State> , INodeRepository{
    private static final Map<String, String> INIT_MAP;
    static {
        INIT_MAP = Maps.newHashMap();
        INIT_MAP.put("ddd-top", "{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top\",\"basePackage\":\"com.jxin.plug.core.level.tmp.top\",\"childNode\":[{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/ohs\",\"childNode\":[{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/ohs/job\",\"childNode\":[],\"dir\":true,\"name\":\"job\",\"type\":\"DIR\"},{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/ohs/subscriber\",\"childNode\":[],\"dir\":true,\"name\":\"subscriber\",\"type\":\"DIR\"},{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/ohs/rpc\",\"childNode\":[],\"dir\":true,\"name\":\"rpc\",\"type\":\"DIR\"},{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/ohs/package-info.java\",\"context\":\"/**\\n *\\n * 接入层\\n * 访问服务的网关\\n * @author Jxin\\n */\\npackage com.jxin.plug.core.level.tmp.top.ohs;\",\"dir\":false,\"name\":\"package-info.java\",\"type\":\"FILE\"}],\"dir\":true,\"name\":\"ohs\",\"type\":\"DIR\"},{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/application\",\"childNode\":[{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/application/assembler\",\"childNode\":[],\"dir\":true,\"name\":\"assembler\",\"type\":\"DIR\"},{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/application/scene\",\"childNode\":[],\"dir\":true,\"name\":\"scene\",\"type\":\"DIR\"},{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/application/package-info.java\",\"context\":\"/**\\n * 应用层\\n * @author Jxin\\n */\\npackage com.jxin.plug.core.level.tmp.top.application;\",\"dir\":false,\"name\":\"package-info.java\",\"type\":\"FILE\"}],\"dir\":true,\"name\":\"application\",\"type\":\"DIR\"},{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/infrastructure\",\"childNode\":[{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/infrastructure/util\",\"childNode\":[],\"dir\":true,\"name\":\"util\",\"type\":\"DIR\"},{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/infrastructure/aop\",\"childNode\":[],\"dir\":true,\"name\":\"aop\",\"type\":\"DIR\"},{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/infrastructure/plug\",\"childNode\":[],\"dir\":true,\"name\":\"plug\",\"type\":\"DIR\"},{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/infrastructure/package-info.java\",\"context\":\"/**\\n *\\n * 基础层\\n * 1.外部框架的依赖 (代码级别)\\n * 2.外部中间件服务的依赖 (进程外级别)\\n * @author Jxin\\n */\\npackage com.jxin.plug.core.level.tmp.top.infrastructure;\",\"dir\":false,\"name\":\"package-info.java\",\"type\":\"FILE\"}],\"dir\":true,\"name\":\"infrastructure\",\"type\":\"DIR\"},{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/domain\",\"childNode\":[{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/domain/aggregation\",\"childNode\":[{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/domain/aggregation/demo\",\"childNode\":[],\"dir\":true,\"name\":\"demo\",\"type\":\"DIR\"}],\"dir\":true,\"name\":\"aggregation\",\"type\":\"DIR\"},{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/domain/service\",\"childNode\":[{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/domain/service/package-info.java\",\"context\":\"/**\\n *\\n * 领域服务层\\n * @author Jxin\\n * @version 1.0\\n * @since 2020/9/4 20:32\\n */\\npackage com.jxin.plug.core.level.tmp.top.domain.service;\",\"dir\":false,\"name\":\"package-info.java\",\"type\":\"FILE\"}],\"dir\":true,\"name\":\"service\",\"type\":\"DIR\"},{\"absolutePath\":\"/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top/domain/package-info.java\",\"context\":\"/**\\n * 领域层\\n * @author Jxin\\n */\\npackage com.jxin.plug.core.level.tmp.top.domain;\",\"dir\":false,\"name\":\"package-info.java\",\"type\":\"FILE\"}],\"dir\":true,\"name\":\"domain\",\"type\":\"DIR\"}],\"dir\":false,\"name\":\"top\",\"type\":\"ROOT\"}");

    }
    @Setter
    private State state = new State(Maps.newHashMap(INIT_MAP));
    @Nullable
    @Override
    public State getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull State state) {
        this.state = state;
    }
    @Override
    public Set<String> allTmpKey(){
        return getLevelTmpRep().keySet();
    }

    @Override
    public Set<String> allTmpKeyWithoutInitKey() {
        return getLevelTmpRep().keySet().stream().filter(key -> !INIT_MAP.containsKey(key)).collect(Collectors.toSet());
    }

    @Override
    public Node get(String key) {
        return Optional.ofNullable(getLevelTmpRep().get(key))
                        .map(Node::of)
                        .orElse(null);
    }
    @Override
    public void remove(String key) {
        getLevelTmpRep().remove(key);
        initState();
    }
    @Override
    public void add(String key, Node node) {
        getLevelTmpRep().put(key, node.dump());
        initState();
    }

    private void initState() {
        state = new State(getLevelTmpRep());
    }

    private Map<String, String> getLevelTmpRep(){
        return state.levelTmpRep;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class State{
        private Map<String, String> levelTmpRep = Maps.newHashMap();
    }
}
