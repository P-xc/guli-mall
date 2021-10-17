package com.study.mall.member.controller;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.study.mall.member.entity.MemberCollectSubjectEntity;
import com.study.mall.member.service.IMemberCollectSubjectService;
import com.study.mall.common.utils.PageUtils;
import com.study.mall.common.utils.R;


/**
 * 会员收藏的专题活动
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:15:57
 */
@RestController
@RequestMapping("member/membercollectsubject")
public class MemberCollectSubjectController {

    @Resource
    private IMemberCollectSubjectService memberCollectSubjectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //member:membercollectsubject:list
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberCollectSubjectService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //member:membercollectsubject:info
    public R info(@PathVariable("id") Long id) {
            MemberCollectSubjectEntity memberCollectSubject = memberCollectSubjectService.getById(id);
        return R.ok().put("memberCollectSubject", memberCollectSubject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //member:membercollectsubject:save
    public R save(@RequestBody MemberCollectSubjectEntity memberCollectSubject) {
            memberCollectSubjectService.save(memberCollectSubject);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //member:membercollectsubject:update
    public R update(@RequestBody MemberCollectSubjectEntity memberCollectSubject) {
            memberCollectSubjectService.updateById(memberCollectSubject);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:membercollectsubject:delete")
    public R delete(@RequestBody Long[] ids) {
            memberCollectSubjectService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
