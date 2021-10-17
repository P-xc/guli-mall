package com.study.mall.product.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.product.entity.SkuInfoEntity;
import com.study.mall.product.service.ISkuInfoService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * sku信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 02:17:55
 */
@RestController
@RequestMapping("product/skuinfo")
public class SkuInfoController {

    @Resource
    private ISkuInfoService skuInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //product:skuinfo:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = skuInfoService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{skuId}")
    //product:skuinfo:info
    public R info(@PathVariable("skuId") Long skuId) {
            SkuInfoEntity skuInfo = skuInfoService.getById(skuId);
        return R.ok().put("skuInfo", skuInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //product:skuinfo:save
    public R save(@RequestBody SkuInfoEntity skuInfo) {
            skuInfoService.save(skuInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //product:skuinfo:update
    public R update(@RequestBody SkuInfoEntity skuInfo) {
            skuInfoService.updateById(skuInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:skuinfo:delete")
    public R delete(@RequestBody Long[] skuIds) {
            skuInfoService.removeByIds(Arrays.asList(skuIds));
        return R.ok();
    }

}
