package com.eastwood.design.pattern.template;

//导出excel的代码模板
public class Test {

    public static void main(String[] args) {
        ExportExcel exportExcel = new ExportExcel();
        exportExcel.export();
    }




}

//导出excel的代码模板
abstract class AbstractExportExcel {

    public void export() {
        //1.查询数据
        //2.生成excel
        //3.导出excel
    }

    protected abstract void generateExcel();

    protected abstract void exportExcel();

}

class ExportExcel extends AbstractExportExcel {

    @Override
    protected void generateExcel() {
        //生成excel
    }

    @Override
    protected void exportExcel() {
        //导出excel
    }

}
