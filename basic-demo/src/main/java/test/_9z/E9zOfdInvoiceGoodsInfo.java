package test._9z;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>实体类</p>
 * <p>ofd电子发票商品信息</p>
 *
 * @since 2020-09-02 04:12:35
 */
@Setter
@Getter
public class E9zOfdInvoiceGoodsInfo {

    //项目名称
    private String item;
    //规格型号
    private String specification;
    //单位
    private String measurementDimension;
    //单价
    private Double price;
    //数量
    private Integer quantity;
    //金额
    private Double amount;
    //税率
    private String taxScheme;
    //税额
    private Double taxAmount;

}
