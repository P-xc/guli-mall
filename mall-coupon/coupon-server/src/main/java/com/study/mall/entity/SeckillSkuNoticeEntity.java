package com.study.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;
    
/**
 * 秒杀商品通知订阅
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2021-10-10 14:06:24
 */
@Data
@TableName("sms_seckill_sku_notice")
public class SeckillSkuNoticeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @TableId
    private Long id;

    /**
    * member_id
    */
    private Long memberId;

    /**
    * sku_id
    */
    private Long skuId;

    /**
    * 活动场次id
    */
    private Long sessionId;

    /**
    * 订阅时间
    */
    private LocalDateTime subcribeTime;

    /**
    * 发送时间
    */
    private LocalDateTime sendTime;

    /**
    * 通知方式[0-短信，1-邮件]
    */
    private Integer noticeType;

    
    private static final String ID = "id";

    private static final String MEMBER_ID = "member_id";

    private static final String SKU_ID = "sku_id";

    private static final String SESSION_ID = "session_id";

    private static final String SUBCRIBE_TIME = "subcribe_time";

    private static final String SEND_TIME = "send_time";

    private static final String NOTICE_TYPE = "notice_type";

    
}
