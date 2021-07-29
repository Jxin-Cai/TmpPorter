package com.jxin.plug.core.ddd.repository;

import com.google.common.collect.Maps;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.jxin.plug.core.ddd.mode.Node;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 节点仓储层
 * @author Jxin
 * @since 2021/7/28 8:55 下午
 */
@State(name = "nodeRepository", storages = @Storage("level-tmp.xml"))
@EqualsAndHashCode
public class NodeRepository implements PersistentStateComponent<NodeRepository.State> , INodeRepository{
    @Setter
    private State state = new State(Maps.newHashMap());
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
