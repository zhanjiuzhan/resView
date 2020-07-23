package org.jpcl.resview.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ExcelUtil {
    public static <T extends Object> List<T> readExcelObject(MultipartFile file, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        Workbook workbook = null;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e) {
            throw new RuntimeException("文件读取异常");
        }

        if (file.getOriginalFilename().endsWith(".xls")) {
            workbook = xls(inputStream);
        } else if (file.getOriginalFilename().endsWith("xlsx")) {
            workbook = xlsx(inputStream);
        } else {
            throw new RuntimeException("非Excel文件");
        }

        // 取得第一个sheet页
        Sheet sheet = workbook.getSheetAt(0);

        // 取得总行数
        int rows = sheet.getLastRowNum() + 1;
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            //此处用来过滤空行
            Cell cell0 = row.getCell( 0);
            cell0.setCellType(CellType.STRING);
            Cell cell1 = row.getCell( 1);
            cell1.setCellType(CellType.STRING);
            if (cell0.getStringCellValue() == "" && cell1.getStringCellValue() == "") {
                continue;
            }
            try {
                T obj = clazz.newInstance();
                for (Field f : fields) {
                    f.setAccessible(true);
                    if (f.isAnnotationPresent(ExcelAnnotation.class)) {
                        ExcelAnnotation excelAnnotation = f.getAnnotation(ExcelAnnotation.class);
                        int columnIndex = excelAnnotation.columnIndex();
                        Cell cell = row.getCell(columnIndex);
                        setFieldValue(obj, f, cell);
                    }
                }
                list.add(obj);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("excel文件内容出错");
            }
        }
        try {
            workbook.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void setFieldValue(Object obj, Field f, Cell cell) {
        try {
            cell.setCellType(CellType.STRING);
            if (f.getType() == byte.class || f.getType() == Byte.class) {
                f.set(obj, Byte.parseByte(cell.getStringCellValue()));
            } else if (f.getType() == int.class || f.getType() == Integer.class) {
                f.set(obj, Integer.parseInt(cell.getStringCellValue()));
            } else if (f.getType() == Double.class || f.getType() == double.class) {
                f.set(obj, Double.parseDouble(cell.getStringCellValue()));
            } else if (f.getType() == BigDecimal.class) {
                f.set(obj, new BigDecimal(cell.getStringCellValue()));
            } else {
                f.set(obj, cell.getStringCellValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对excel 2003处理
     */
    private static Workbook xls(InputStream is) {
        try {
            return new HSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对excel 2007处理
     */
    private static Workbook xlsx(InputStream is) {
        try {
            return new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
