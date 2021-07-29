package com.jxin.plug.core.level.handle;

import com.jxin.plug.core.level.mode.Node;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 生成模板测试类,以便于打印一些初始模板
 * @author 素律
 * @since 2021/7/29 8:23 下午
 */
public class TempHandleTest {

    @Test
    public void createTemp() {
        final Node temp = TempHandle.createTemp("/Users/jxin/javaPro/work/TmpPorter/src/test/java/com/jxin/plug/core/level/tmp/top", "com.jxin.plug.core.level.tmp.top");
        System.out.println(temp.dump());
    }
}