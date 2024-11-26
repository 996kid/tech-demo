package test._9z;

import cn.hutool.json.JSONUtil;
import jodd.io.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 996kid@gmail.com
 * @Description OFDFileParser
 * @Date 2024/06/20 16:55
 */
@Slf4j
public class OFDFileParser {

    public static void main(String[] args) {
//        File file = new File("D:\\Documents\\WeChat Files\\wxid_ipqtd231vacl21\\FileStorage\\File\\2024-06\\专票\\dzfp_24342000000063647181_安徽国德智能仓储装备有限公司_20240617094059.ofd");
//        File file = new File("D:\\Documents\\WeChat Files\\wxid_ipqtd231vacl21\\FileStorage\\File\\2024-06\\普通发票\\dzfp_24312000000146744201_上海朴极网络科技有限公司_20240614141447.ofd");
//        File file = new File("D:\\Documents\\WeChat Files\\wxid_ipqtd231vacl21\\FileStorage\\File\\2024-06\\dzfp_24432000000069864714_长沙国赫汽车服务有限公司_20240617093827.ofd");
//        File file = new File("D:\\Documents\\WeChat Files\\wxid_ipqtd231vacl21\\FileStorage\\File\\2024-06\\开具发票\\dzfp_24322000000169948602_平湖吉康机械配件有限公司_20240614140914.ofd");
//        File file = new File("D:\\Documents\\WeChat Files\\wxid_ipqtd231vacl21\\FileStorage\\File\\2024-06\\取得发票1\\dzfp_24322000000169895617_扬州齐南电气科技有限公司_20240614141448.ofd");
//        File file = new File("D:\\Documents\\WeChat Files\\wxid_ipqtd231vacl21\\FileStorage\\File\\2024-06\\取得发票2\\dzfp_24507000000016229300_重庆京东创盟信息技术有限公司_20240614141642.ofd");
//        File file = new File("D:\\Documents\\WeChat Files\\wxid_ipqtd231vacl21\\FileStorage\\File\\2024-06\\开具发票\\发票批量下载_20240614141115\\dzfp_24322000000167482078_福州易施特自动化科技有限公司_20240614141112.ofd");
        File file = new File("D:\\Documents\\WeChat Files\\wxid_ipqtd231vacl21\\FileStorage\\File\\2024-06\\开具发票\\发票批量下载_20240614141147\\dzfp_24322000000163484604_南京双仕达机电科技有限公司_20240614141143.ofd");
        E9zOfdInvoiceMainInfo mainInfo = new E9zOfdInvoiceMainInfo();
        parse(file, mainInfo);
        System.out.println(JSONUtil.toJsonStr(mainInfo));
    }

    public static void parse(File zipFile, E9zOfdInvoiceMainInfo mainInfo) {
        File parentFile = zipFile.getParentFile();
        // 避免多个文件解析时互相覆盖
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 解压后暂存文件夹
        File tempFile = new File(parentFile.getAbsolutePath() + "/" + uuid);
        try {
            // 解压到指定文件夹
            ZipUtil.unzip(zipFile, tempFile);
            File tagsXml = new File(parentFile.getAbsolutePath() + "/" + uuid + "/Doc_0/tags/CustomTag.xml");
            File pages = new File(parentFile.getAbsolutePath() + "/" + uuid + "/Doc_0/Pages");
            File[] files = pages.listFiles();
            if (files.length > 0) {
                SAXReader reader = new SAXReader();
                // 合并商品信息
                List<Element> allElements = new ArrayList<>();
                List<E9zOfdInvoiceGoodsInfo> goodsInfos = new ArrayList<>();
                Map<String, String> mapping = new HashMap<>();
                getValueMapping(files, reader, allElements, mapping);
                // 读取 CustomTag.xml
                Document document = reader.read(tagsXml);
                Element root = document.getRootElement();
                setMainInfo(mainInfo, mapping, root);
                // 第一部分商品信息
                setGoodsInfos1(goodsInfos, mapping, root);
                int preIndex = goodsInfos.size() - 1;
                // 最后一页商品信息
                setGoodsInfos2(goodsInfos, mapping, root, preIndex);
                mainInfo.setE9zOfdInvoiceGoodsInfo(goodsInfos);
            }
        } catch (Exception e) {
            log.error("OFD file parse failed.", e);
        } finally {
            // 删除暂存文件
            delete(tempFile);
        }
    }

    /**
     * 删除暂存的解压后的文件
     * @param file
     */
    public static void delete(File file){
        if(file.isDirectory()) {
            File[] subs = file.listFiles();
            for (int i = 0; i < subs.length; i++) {
                File sub = subs[i];
                delete(sub);
            }
        }
        file.delete();
    }

    /**
     * 解析最后一页特殊的商品信息
     * @param goodsInfos
     * @param mapping
     * @param root
     * @param preIndex
     */
    private static void setGoodsInfos2(List<E9zOfdInvoiceGoodsInfo> goodsInfos, Map<String, String> mapping, Element root, int preIndex) {
        List<Node> nodes = root.selectNodes("/ofd:root/ofd:Item");
        // 上一页的最后一条记录的itemname
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < nodes.size(); i++) {
            // 获取第一个 ofd:item 判断后面相邻的兄弟节点是不是TaxScheme
            // 不是则是上一页最后一条商品的item
            Node node = nodes.get(i);
            // 获取所有后置的兄弟节点
            List<Node> n1 = node.selectNodes("following-sibling::*");
            if (n1.get(0).getName().equals("Item")) {
                stringBuffer.append(mapping.get(node.getStringValue()));
            } else {
                // TaxScheme
                Element element = (Element) node;
                List<Element> elements = element.elements("ObjectRef");
                StringBuffer itemName = new StringBuffer();
                for (Element e : elements) {
                    itemName.append(mapping.get(e.getStringValue()));
                }
                E9zOfdInvoiceGoodsInfo goodsInfo = new E9zOfdInvoiceGoodsInfo();
                goodsInfo.setItem(itemName.toString());
                List<Node> goodsInfoNodes;

                int endIndex = n1.size();
                for (int j = 1; j < n1.size(); j++) {
                    if (n1.get(j).getName().equals("Item")) {
                        endIndex = j;
                        break;
                    }
                }
                goodsInfoNodes = n1.subList(0, endIndex);
                for (Node n : goodsInfoNodes) {
                    Element e = (Element) n;
                    String value = mapping.get(e.elements("ObjectRef").get(0).getStringValue());
                    switch (e.getName()) {
                        case "Specification":
                            goodsInfo.setSpecification(value);
                            break;
                        case "TaxScheme":
                            goodsInfo.setTaxScheme(value);
                            break;
                        case "MeasurementDimension":
                            goodsInfo.setMeasurementDimension(value);
                            break;
                        case "Price":
                            goodsInfo.setPrice(Double.parseDouble(value));
                            break;
                        case "Quantity":
                            if (value.contains(".")) {
                                goodsInfo.setQuantity(Integer.parseInt(value.substring(0, value.indexOf("."))));
                            } else {
                                goodsInfo.setQuantity(Integer.parseInt(value));
                            }
                            break;
                        case "Amount":
                            goodsInfo.setAmount(Double.parseDouble(value));
                            break;
                        case "TaxAmount":
                            goodsInfo.setTaxAmount(Double.parseDouble(value));
                            break;
                    }
                }
                goodsInfos.add(goodsInfo);
            }
        }
        if (stringBuffer.length() > 0) {
            E9zOfdInvoiceGoodsInfo special = goodsInfos.get(preIndex);
            special.setItem(special.getItem() + stringBuffer);
        }
    }

    /**
     * 解析 GoodsInfos 元素
     * @param goodsInfos
     * @param mapping
     * @param root
     */
    private static void setGoodsInfos1(List<E9zOfdInvoiceGoodsInfo> goodsInfos, Map<String, String> mapping, Element root) {
        List<Element> goodsInfoElements = root.elements("GoodsInfos");
        for (Element element : goodsInfoElements) {
            E9zOfdInvoiceGoodsInfo goodsInfo = new E9zOfdInvoiceGoodsInfo();
            Element itemElement = element.element("Item");
            List<Element> objectRefElements = itemElement.elements("ObjectRef");
            StringBuffer item = new StringBuffer();
            for (Element e : objectRefElements) {
                item.append(mapping.get(e.getStringValue()));
            }
            goodsInfo.setItem(item.toString());

            Element taxSchemeElement = element.element("TaxScheme");
            if (taxSchemeElement != null) {
                goodsInfo.setTaxScheme(mapping.get(taxSchemeElement.getStringValue()));
            }

            Element measurementDimensionElement = element.element("MeasurementDimension");
            if (measurementDimensionElement != null) {
                goodsInfo.setMeasurementDimension(mapping.get(measurementDimensionElement.getStringValue()));
            }

            Element amountElement = element.element("Amount");
            if (amountElement != null) {
                goodsInfo.setAmount(Double.parseDouble(mapping.get(amountElement.getStringValue())));
            }

            Element taxAmountElement = element.element("TaxAmount");
            if (taxAmountElement != null) {
                goodsInfo.setTaxAmount(Double.parseDouble(mapping.get(taxAmountElement.getStringValue())));
            }

            Element priceElement = element.element("Price");
            if (priceElement != null) {
                goodsInfo.setPrice(Double.parseDouble(mapping.get(priceElement.getStringValue())));
            }

            Element quantityElement = element.element("Quantity");
            if (quantityElement != null) {
                String quantity = mapping.get(quantityElement.getStringValue());
                if (quantity.contains(".")) {
                    // 有出现数量为 1.000000的情况 特殊处理
                    goodsInfo.setQuantity(Integer.parseInt(quantity.substring(0, quantity.indexOf("."))));
                } else {
                    goodsInfo.setQuantity(Integer.parseInt(mapping.get(quantityElement.getStringValue())));
                }
            }

            // specification 规格型号
            Element specificationElement = element.element("Specification");
            if (specificationElement != null) {
                goodsInfo.setSpecification(mapping.get(specificationElement.getStringValue()));
            }
            goodsInfos.add(goodsInfo);
        }
    }

    /**
     * 生成 ID 到 实际内容的映射
     * @param files
     * @param reader
     * @param allElements
     * @param mapping
     * @throws DocumentException
     */
    private static void getValueMapping(File[] files, SAXReader reader, List<Element> allElements, Map<String, String> mapping) throws DocumentException {
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            File[] contentXmlFiles = file.listFiles();
            File contentXmlFile = contentXmlFiles[0];
            Document document = reader.read(contentXmlFile);
            Element root = document.getRootElement();
            Element contentElement = root.element("Content");
            Element layerElement = contentElement.element("Layer");
            List<Element> textObjectList = layerElement.elements("TextObject");
            allElements.addAll(textObjectList);
        }


        for (Element e : allElements) {
            Element textCode = e.element("TextCode");
            // ID -> 元素文本值
            mapping.put(e.attributeValue("ID"), textCode.getStringValue());
        }
    }

    /**
     * 设置发票主要信息
     * @param mainInfo
     * @param mapping
     * @param root
     */
    private static void setMainInfo(E9zOfdInvoiceMainInfo mainInfo, Map<String, String> mapping, Element root) {
        Element invoiceNoElement = (Element) root.selectNodes("/ofd:root/ofd:InvoiceNo").get(0);
        mainInfo.setInvoiceNo(mapping.get(invoiceNoElement.elements("ObjectRef").get(0).getStringValue()));
        Element issueDateElement = (Element) root.selectNodes("/ofd:root/ofd:IssueDate").get(0);
        mainInfo.setIssueDate(mapping.get(issueDateElement.elements("ObjectRef").get(0).getStringValue()));

        Element buyerElement = (Element) root.selectNodes("/ofd:root/ofd:Buyer/ofd:BuyerName").get(0);
        mainInfo.setBuyerName(mapping.get(buyerElement.elements("ObjectRef").get(0).getStringValue()));

        Element buyerTaxIDElement = (Element) root.selectNodes("/ofd:root/ofd:Buyer/ofd:BuyerTaxID").get(0);
        mainInfo.setBuyerTaxID(mapping.get(buyerTaxIDElement.elements("ObjectRef").get(0).getStringValue()));

        Element sellerNameElement = (Element) root.selectNodes("/ofd:root/ofd:Seller/ofd:SellerName").get(0);
        mainInfo.setSellerName(mapping.get(sellerNameElement.elements("ObjectRef").get(0).getStringValue()));

        Element sellerTaxIDElement = (Element) root.selectNodes("/ofd:root/ofd:Seller/ofd:SellerTaxID").get(0);
        mainInfo.setSellerTaxID(mapping.get(sellerTaxIDElement.elements("ObjectRef").get(0).getStringValue()));

        Element taxExclusiveTotalAmountElement = (Element) root.selectNodes("/ofd:root/ofd:TaxExclusiveTotalAmount").get(0);
        mainInfo.setTaxExclusiveTotalAmount(Double.parseDouble(mapping.get(taxExclusiveTotalAmountElement.elements("ObjectRef").get(1).getStringValue())));

        Element taxTotalAmountElement = (Element) root.selectNodes("/ofd:root/ofd:TaxTotalAmount").get(0);
        mainInfo.setTaxTotalAmount(Double.parseDouble(mapping.get(taxTotalAmountElement.elements("ObjectRef").get(1).getStringValue())));

        Element invoiceClerkElement = (Element) root.selectNodes("/ofd:root/ofd:InvoiceClerk").get(0);
        mainInfo.setInvoiceClerk(mapping.get(invoiceClerkElement.elements("ObjectRef").get(0).getStringValue()));

        mainInfo.setTaxInclusiveTotalAmount(new BigDecimal(mainInfo.getTaxExclusiveTotalAmount() + "")
                .add(new BigDecimal(mainInfo.getTaxTotalAmount() + "")).doubleValue());
    }
}
