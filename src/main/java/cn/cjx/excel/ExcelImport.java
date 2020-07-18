package cn.cjx.excel;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;
import cn.afterturn.easypoi.exception.excel.ExcelImportException;
import org.apache.poi.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @创建日期: 2020/7/18 10:33
 * @创建人:陈俊旋
 */
public class ExcelImport {

    public static void main(String[] args) {
        URL xmlpath = ExcelImport.class.getClassLoader().getResource("ExcelImport.java");
        System.out.println(xmlpath);
//        System.out.println(ExcelImport.class.getClass().getResource("/").getPath());
//        List<User> list = new ArrayList<>(100000);
//        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班用户","用户"), User.class, list);
//        OutputStream out = null;
//        try {
//            out = new FileOutputStream(ExcelImport.class.getClass().getResource("/").getPath());
//            workbook.write(out);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Excel 导入 数据源本地文件,不返回校验结果 导入 字 段类型 Integer,Long,Double,Date,String,Boolean
     *
     * @param file
     * @param pojoClass
     * @param params
     * @return
     */
    public static <T> List<T> importExcel(File file, Class<?> pojoClass, ImportParams params) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            return new ExcelImportService().importExcelByIs(in, pojoClass, params, false).getList();
        } catch (ExcelImportException e) {
            throw new ExcelImportException(e.getType(), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcelImportException(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * Excel 导入 数据源IO流,不返回校验结果 导入 字段类型 Integer,Long,Double,Date,String,Boolean
     *
     * @param inputstream
     * @param pojoClass
     * @param params
     * @return
     * @throws Exception
     */
    public static <T> List<T> importExcel(InputStream inputstream, Class<?> pojoClass,
                                          ImportParams params) throws Exception {
        return new ExcelImportService().importExcelByIs(inputstream, pojoClass, params, false).getList();
    }
}
