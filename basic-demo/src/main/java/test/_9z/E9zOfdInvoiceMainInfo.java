package test._9z;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>实体类</p>
 * <p>ofd电子发票信息</p>
 *
 * @since 2020-09-02 04:12:35
 */
@Setter
@Getter
public class E9zOfdInvoiceMainInfo {
    //电子发票商品信息
    List<E9zOfdInvoiceGoodsInfo> E9zOfdInvoiceGoodsInfo;
    //发票代码
    private String invoiceCode;
    //发票号码
    private String invoiceNo;
    //开票日期
    private String issueDate;
    //校验码
    private String invoiceCheckCode;
    //机器码
    private String machineNo;
    //密码区
    private String taxControlCode;
    //购买方名称
    private String buyerName;
    //购买方纳税人识别号
    private String buyerTaxID;
    //购买方地址电话
    private String buyerAddrTel;
    //购买方开户行及账号
    private String buyerFinancialAccount;
    //销售方名称
    private String sellerName;
    //销售方纳税人识别号
    private String sellerTaxID;
    //销售方地址电话
    private String sellerAddrTel;
    //销售方开户行及账号
    private String sellerFinancialAccount;
    //价税合计(小写)
    private Double taxInclusiveTotalAmount;
    //开票人
    private String invoiceClerk;
    //收款人
    private String payee;
    //复核人
    private String checker;
    //合计税额
    private Double taxTotalAmount;
    //合计金额
    private Double taxExclusiveTotalAmount;
}
