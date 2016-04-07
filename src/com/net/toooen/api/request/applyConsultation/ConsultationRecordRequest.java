package com.net.toooen.api.request.applyConsultation;

import javax.validation.constraints.NotNull;

import com.net.toooen.api.request.Request;

/**
 * @ClassName: ConsultationRecordRequest
 * @Description: 会诊记录
 * @author wangshaofeng
 * @date 2016年3月12日 下午1:03:35
 */
public class ConsultationRecordRequest extends Request {

    private static final long serialVersionUID = -6564534052213841290L;

    /**
     * @Fields userId : 用户ID
     */
    private Integer userId;

    /**
     * @Fields recordType : 记录类型;1为发起,2为受邀
     */
    private Integer recordType;

    /**
     * @Fields pageIndex : 分页索引
     */
    private Integer pageIndex;

    @NotNull
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @NotNull
    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    @NotNull
    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

}
