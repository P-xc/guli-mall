package com.study.mall.service.impl;

import com.study.mall.entity.HomeSubjectSpuEntity;
import com.study.mall.mapper.HomeSubjectSpuMapper;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.service.IHomeSubjectSpuService;

/**
 * 专题商品
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@Service("homeSubjectSpuService")
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuMapper, HomeSubjectSpuEntity> implements IHomeSubjectSpuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HomeSubjectSpuEntity> page = this.page(
                new Query<HomeSubjectSpuEntity>().getPage(params),
                new QueryWrapper<HomeSubjectSpuEntity>()
        );
        return new PageUtils(page);
    }

}