package org.jeecg.common.relationsp.service;

import org.jeecg.common.relationsp.entity.Relationsp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * @Description: 特殊关系的处理
 * @Author: jeecg-boot
 * @Date:   2019-11-03
 * @Version: V1.0
 */
public interface IRelationspService extends IService<Relationsp> {
    public Set<String> getDirectsonID(String managerid) ;
}
