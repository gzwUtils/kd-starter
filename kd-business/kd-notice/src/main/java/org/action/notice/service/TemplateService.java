package org.action.notice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.action.notice.entity.TemplateInfo;
import org.action.notice.kd.mapper.TemplateMapper;
import org.springframework.stereotype.Service;

/**
 * @author gzw
 * @description：
 * @since：2024/8/2 19:36
 */
@Service
public class TemplateService extends ServiceImpl<TemplateMapper, TemplateInfo> {

    public TemplateInfo QueryTemplateInfo(String tempId) {
        QueryWrapper<TemplateInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("temp_id", tempId);
        return this.getOne(wrapper);
    }



}
