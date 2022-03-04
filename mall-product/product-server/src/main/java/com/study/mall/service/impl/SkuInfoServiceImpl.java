package com.study.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.Query;
import com.study.mall.entity.SkuImagesEntity;
import com.study.mall.entity.SkuInfoEntity;
import com.study.mall.entity.SpuInfoDescEntity;
import com.study.mall.mapper.SkuInfoMapper;
import com.study.mall.service.IAttrGroupService;
import com.study.mall.service.ISkuImagesService;
import com.study.mall.service.ISkuInfoService;
import com.study.mall.service.ISpuInfoDescService;
import com.study.mall.vo.SkuItemVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * sku信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@Service("skuInfoService")
@Transactional(rollbackFor = Exception.class)
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfoEntity> implements ISkuInfoService {

    @Resource
    private ISkuImagesService skuImagesService;

    @Resource
    private ISpuInfoDescService spuInfoDescService;

    @Resource
    private IAttrGroupService attrGroupService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SkuInfoEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            wrapper.and(w ->
                    w.eq(SkuInfoEntity.SKU_ID, key).or().like(SkuInfoEntity.SKU_NAME, key));
        }
        String catelogId = (String) params.get("catelogId");
        if (StringUtils.isNotBlank(catelogId) && !"0".equals(catelogId)) {
            wrapper.eq(SkuInfoEntity.CATALOG_ID, catelogId);
        }
        String brandId = (String) params.get("brandId");
        if (StringUtils.isNotBlank(brandId) && !"0".equals(brandId)) {
            wrapper.eq(SkuInfoEntity.BRAND_ID, brandId);
        }
        String min = (String) params.get("min");
        if (StringUtils.isNotBlank(min)) {
            wrapper.ge(SkuInfoEntity.PRICE, min);
        }
        String maxStr = (String) params.get("max");
        if (StringUtils.isNotBlank(maxStr)) {
            BigDecimal max = new BigDecimal(maxStr);
            if (max.compareTo(BigDecimal.valueOf(0)) > 0) {
                wrapper.le(SkuInfoEntity.PRICE, max);
            }
        }
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public List<SkuInfoEntity> getBySpuId(Long spuId) {
        return list(new QueryWrapper<SkuInfoEntity>().eq(SkuInfoEntity.SPU_ID, spuId));
    }

    @Override
    public SkuItemVo item(Long skuId) {
        SkuItemVo skuItemVo = new SkuItemVo();
        //sku基本信息
        SkuInfoEntity skuInfoEntity = getById(skuId);
        Long spuId = skuInfoEntity.getSpuId();
        Long catalogId = skuInfoEntity.getCatalogId();
        skuItemVo.setInfo(skuInfoEntity);
        //sku图片
        List<SkuImagesEntity> images = skuImagesService.getBySkuId(skuId);
        skuItemVo.setImages(images);
        //spu介绍
        for (SkuImagesEntity image : images) {
            System.out.println(image.getId());
            System.out.println(image.getImgUrl());
            System.out.println(image.getDefaultImg());
            System.out.println(image.getImgSort());
        }
        SpuInfoDescEntity descEntity = spuInfoDescService.getById(spuId);
        skuItemVo.setDesp(descEntity);
        //spu销售属性组合
        List<SkuItemVo.SpuItemAttrGroupVo> attrGroupVos = attrGroupService.getWithAttrBySpuId(spuId, catalogId);
        skuItemVo.setGroupAttrs(attrGroupVos);
        //spu规格参数
        return skuItemVo;
    }

}