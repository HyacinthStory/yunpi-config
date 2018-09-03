/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018/9/2 20:34
 * 审核人员: 
 * 相关文档: 
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.hyacinth.yunpi.common.enums;

/**
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
public enum PaymentTypeEnum {
    /**
     * Online pay payment type enum.
     */
    ONLINE_PAY(1, "在线支付");

    PaymentTypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    private String value;
    private int code;

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Code of payment type enum.
     *
     * @param code the code
     *
     * @return the payment type enum
     */
    public static PaymentTypeEnum codeOf(int code) {
        for (PaymentTypeEnum paymentTypeEnum : values()) {
            if (paymentTypeEnum.getCode() == code) {
                return paymentTypeEnum;
            }
        }
        return null;
    }

}