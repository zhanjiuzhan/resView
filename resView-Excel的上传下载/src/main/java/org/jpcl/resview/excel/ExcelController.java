package org.jpcl.resview.excel;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@Controller
public class ExcelController {

    @PostMapping("/import.do")
    @ResponseBody
    public String upload(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile excel = multipartRequest.getFile("file");
        List<AssignUser> excelData = ExcelUtil.readExcelObject(excel.getInputStream(), excel.getOriginalFilename(), AssignUser.class);
        List<String> sqls = new ArrayList<>();
        for (AssignUser user : excelData) {
            StringBuilder sbr = new StringBuilder("INSERT INTO ");
        }


        return JSONObject.toJSONString(excelData);
    }

    @GetMapping("/export.do")
    public void export(HttpServletResponse response) throws Exception {
        // 1.读取Excel模板
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "excel/template.xlsx");
        InputStream in = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(in);
        // 2.读取模板里面的所有Sheet
        XSSFSheet sheet = wb.getSheetAt(0);
        // 3.设置公式自动读取
        sheet.setForceFormulaRecalculation(true);
        // 4.向相应的单元格里面设置值
        XSSFRow row;

        row = getRow(sheet, 1);
        getCell(row, 1).setCellValue("张三");
        getCell(row, 3).setCellValue("18");
        getCell(row, 6).setCellValue("本科");
        getCell(row, 8).setCellValue(new Date());

        row = getRow(sheet, 2);
        getCell(row, 1).setCellValue("1511xxxx234");
        getCell(row, 3).setCellValue("广东");
        getCell(row, 6).setCellValue("本科");

        row = getRow(sheet, 3);
        getCell(row, 1).setCellValue("180");
        getCell(row, 3).setCellValue("已婚");
        getCell(row, 6).setCellValue("深圳");
        getCell(row, 8).setCellValue("2");

        row = getRow(sheet, 4);
        getCell(row, 1).setCellValue("60");
        getCell(row, 3).setCellValue("中国");
        getCell(row, 6).setCellValue("其它");
        getCell(row, 8).setCellValue("备注");

        //单元格合并
        row = getRow(sheet, 5);
        getCell(row, 0).setCellValue("合并列");
        CellRangeAddress region = new CellRangeAddress(6, 6, 0, 5);
        sheet.addMergedRegion(region);

        //单元格设置背景色
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        getCell(row, 0).setCellStyle(style);

        //设置单元格边框
        row = getRow(sheet, 8);
        XSSFCellStyle style2 = wb.createCellStyle();
        style2.setBorderBottom(BorderStyle.DOUBLE);
        style2.setBorderRight(BorderStyle.DOUBLE);
        style2.setBorderLeft(BorderStyle.DOUBLE);
        style2.setBorderTop(BorderStyle.DOUBLE);
        style2.setBottomBorderColor(IndexedColors.SKY_BLUE.getIndex());
        style2.setRightBorderColor(IndexedColors.SKY_BLUE.getIndex());
        getCell(row, 0).setCellStyle(style2);

        // 7.设置sheet名称和单元格内容
        wb.setSheetName(0, "测试");

        String fileName = new String(("export-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                .getBytes(), "UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setHeader("Pragma", "No-cache");
        response.setCharacterEncoding("utf-8");
        ServletOutputStream out = response.getOutputStream();
        wb.write(out);
        out.flush();
        out.close();
    }

    private XSSFRow getRow(XSSFSheet sheet, int rowIndex) {
        XSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        return row;
    }

    private XSSFCell getCell(XSSFRow row, int cellIndex) {
        XSSFCell cell = row.getCell(cellIndex);
        if (cell == null) {
            cell = row.createCell(cellIndex);
        }
        return cell;
    }
}
