/**
 *
 * 领域服务层
 * 领域服务的维度与聚合是平级
 *
 * 推到模型的顺序（领域服务应该放在最后，作为不得不才用的存在）：
 * 值对象（Value Object）→ 实体（Entity）→ 领域服务（Domain Service）
 *
 * 建议
 * 任何服务对象在其名称中必须包含一个动词, 以防出现 ${聚合名}Service 这种无明确意义的领域服务定义
 * @author Jxin
 * @version 1.0
 * @since 2020/9/4 20:32
 */
package com.jxin.plug.core.level.tmp.top.domain.service;