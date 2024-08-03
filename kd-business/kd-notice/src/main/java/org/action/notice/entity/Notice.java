package org.action.notice.entity;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.action.entity.BaseEntity;
import org.action.notice.enums.NoticeState;
import org.action.notice.enums.NoticeType;

/**
 * @author gzw
 * @description： 通知类
 * @since：2024/8/2 19:40
 */
@SuppressWarnings("all")
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class Notice extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 通知标题
     */
    private String noticeTitle;

    /**
     * 通知类型
     */
    private NoticeType noticeType;

    /**
     * 通知内容
     */
    private String noticeContent;

    /**
     * 发送成功时间
     */
    private Date sendSuccessTime;
    /**
     * 接收地址
     */
    private String targetAddress;
    /**
     * 状态
     */
    private NoticeState state;

    /**
     * 重试次数
     */
    private int retryTimes;


    /**
     * 扩展信息
     */
    private String extendInfo;

    public void addExtendInfo(String key, String value) {
        Map<String, String> extendInfoMap;
        if (extendInfo == null) {
            extendInfoMap = Maps.newHashMapWithExpectedSize(1);
        } else {
            extendInfoMap = JSON.parseObject(this.extendInfo, Map.class);
        }
        extendInfoMap.put(key, value);
        this.extendInfo = JSON.toJSONString(extendInfoMap);
    }


}
