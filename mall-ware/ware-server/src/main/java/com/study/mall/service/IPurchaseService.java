package com.study.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.entity.PurchaseEntity;
import java.util.Map;

/**
 * 采购信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:20
 */
public interface IPurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询为分配的采购单
     * @param params 查询条件
     * @return 分页信息
     */
    PageUtils queryPageUnreceive(Map<String, Object> params);
}

