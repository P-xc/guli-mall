package com.study.mall.controller;

import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;
import com.study.mall.entity.PurchaseEntity;
import com.study.mall.service.IPurchaseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


/**
 * 采购信息
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 16:34:20
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {

    @Resource
    private IPurchaseService purchaseService;

    @GetMapping("/unreceive/list")
    public R unreceiveList(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseService.queryPageUnreceive(params);
        return R.ok(page);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    //ware:purchase:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //ware:purchase:info
    public R info(@PathVariable("id") Long id) {
        PurchaseEntity purchase = purchaseService.getById(id);
        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //ware:purchase:save
    public R save(@RequestBody PurchaseEntity purchase) {
        purchaseService.save(purchase);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //ware:purchase:update
    public R update(@RequestBody PurchaseEntity purchase) {
        purchaseService.updateById(purchase);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("ware:purchase:delete")
    public R delete(@RequestBody Long[] ids) {
        purchaseService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
