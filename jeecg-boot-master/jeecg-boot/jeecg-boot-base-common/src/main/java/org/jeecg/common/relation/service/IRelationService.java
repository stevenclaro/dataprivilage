package org.jeecg.common.relation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.relation.entity.Relation;

import java.util.List;
import java.util.Set;

/**
 * @Description: 管理关系
 * @Author: jeecg-boot
 * @Date:   2019-11-02
 * @Version: V1.0
 */
public interface IRelationService extends IService<Relation> {
    public Set<String> getAllsonID(String managerid);
}
