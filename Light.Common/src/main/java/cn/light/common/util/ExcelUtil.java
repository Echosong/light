package cn.light.common.util;

import cn.light.common.annotation.ExcelAnnotation;
import cn.light.common.consts.IExtendObject;
import cn.light.common.consts.IntegerConst;
import cn.light.common.dto.ExtendColumnInfo;
import cn.light.common.enums.ExtendColumnTypeEnum;
import cn.light.common.exception.BaseKnownException;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>Title: light</p >
 * <p>Description: ExcelUtil</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2024/1/2 12:05 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
public class ExcelUtil {
    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
    private static final int PROPERTY_NAME_ROW_NUM = 0;
    private static final int PROPERTY_ROW_NUM = 1;
    private static final int VALUE_ROW_START_NUM = 2;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String XLS_FILE_NAME_REGEX = "^.+\\.(?i)(xls)$";
    private static final String XLSX_FILE_NAME_REGEX = "^.+\\.(?i)(xlsx)$";

    public ExcelUtil() {
    }

    public static ResponseEntity<byte[]> generateImportFile(Object example, String tableName) throws IOException, IllegalAccessException {
        List list = new ArrayList();
        list.add(example);
        return generateImportFile((List)list, tableName, (Class)example.getClass());
    }

    public static ResponseEntity<byte[]> generateImportFile(Class clas, String tableName) throws IOException, IllegalAccessException {
        List list = new ArrayList();
        return generateImportFile((List)list, tableName, (Class)clas);
    }

    public static ResponseEntity<byte[]> generateImportFile(List list, String tableName, Class clas) throws IllegalAccessException, IOException {
        List<ExtendColumnInfo> columnInfos = new ArrayList();
        return generateImportFile(list, tableName, clas, columnInfos);
    }

    public static ResponseEntity<byte[]> generateImportFile(Class clas, String tableName, List<ExtendColumnInfo> columnInfos) throws IllegalAccessException, IOException {
        return generateImportFile(new ArrayList(), tableName, clas, columnInfos);
    }

    public static ResponseEntity<byte[]> generateImportFile(List list, String tableName, Class clas, List<ExtendColumnInfo> columnInfos) throws IllegalAccessException, IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        addSheetToWorkBook(list, tableName, clas, columnInfos, hssfWorkbook);
        return generateHttpExcelFile(hssfWorkbook, tableName);
    }

    public static void addSheetToWorkBook(List list, String tableName, Class clas, List<ExtendColumnInfo> columnInfos, HSSFWorkbook hssfWorkbook) throws IllegalAccessException {
        HSSFSheet hssfSheet = hssfWorkbook.createSheet(tableName);
        HSSFRow hssfRowCn = hssfSheet.createRow(0);
        HSSFRow hssfRow = hssfSheet.createRow(1);
        Field[] fields = ReflectUtil.getAllFields(clas);
        HSSFRow[] valueRows = new HSSFRow[list.size()];
        int columnSqu = 0;

        HSSFCell hssfCell;
        int i;
        for(i = 0; i < fields.length; ++i) {
            ExcelAnnotation excelAnnotation = fields[i].getAnnotation(ExcelAnnotation.class);
            if (excelAnnotation != null) {
                if (excelAnnotation.ignore()) {
                    continue;
                }

                if (excelAnnotation.width() != 0 && excelAnnotation.width() > 0) {
                    hssfSheet.setColumnWidth(columnSqu, excelAnnotation.width() * 256);
                }
            }

            Schema apiModelProperties = (Schema)fields[i].getAnnotation(Schema.class);
            if (apiModelProperties != null) {
                if (apiModelProperties.hidden()) {
                    continue;
                }

                hssfCell = hssfRowCn.createCell(columnSqu);
                hssfCell.setCellValue(apiModelProperties.title());
            }

            hssfCell = hssfRow.createCell(columnSqu);
            hssfCell.setCellValue(fields[i].getName());

            for(int j = 0; j < list.size(); ++j) {
                Object value = fields[i].get(list.get(j));
                if (value != null) {
                    if (valueRows[j] == null) {
                        valueRows[j] = hssfSheet.createRow(j + 2);
                    }

                    hssfCell = valueRows[j].createCell(columnSqu);
                    if (fields[i].getType() == Date.class) {
                        hssfCell.setCellValue(SIMPLE_DATE_TIME_FORMAT.format((Date)value));
                    } else {
                        hssfCell.setCellValue(value.toString());
                    }
                }
            }

            ++columnSqu;
        }

        if (columnInfos != null && columnInfos.size() > 0) {
            for(i = 0; i < columnInfos.size(); ++i) {
                ExtendColumnInfo info = (ExtendColumnInfo)columnInfos.get(i);
                hssfCell = hssfRowCn.createCell(columnSqu);
                hssfCell.setCellValue(info.getChName());
                hssfCell = hssfRow.createCell(columnSqu);
                hssfCell.setCellValue(info.getColumnName());
                if (list.size() > 0 && list.get(0) instanceof IExtendObject) {
                    for(int j = 0; j < list.size(); ++j) {
                        IExtendObject iExtendObject = (IExtendObject)list.get(j);
                        hssfCell = valueRows[j].createCell(columnSqu);
                        addCell(hssfCell, info, iExtendObject);
                    }
                }

                ++columnSqu;
            }
        }

    }

    private static void addCell(HSSFCell hssfCell, ExtendColumnInfo info, IExtendObject iExtendObject) {
        Map<String, Object> map = iExtendObject.getExtend();
        if (map != null) {
            Object extendProperty = map.get(info.getColumnName());
            if (extendProperty != null) {
                if (info.getColumnType().toUpperCase().equals(ExtendColumnTypeEnum.DATE.toString())) {
                    hssfCell.setCellValue(SIMPLE_DATE_FORMAT.format(iExtendObject.getExtend().get(info.getColumnName())));
                }

                if (info.getColumnType().toUpperCase().equals(ExtendColumnTypeEnum.DATETIME.toString())) {
                    hssfCell.setCellValue(SIMPLE_DATE_TIME_FORMAT.format(iExtendObject.getExtend().get(info.getColumnName())));
                } else {
                    hssfCell.setCellValue(iExtendObject.getExtend().get(info.getColumnName()).toString());
                }
            }
        }

    }

    public static ResponseEntity<byte[]> generateHttpExcelFile(HSSFWorkbook hssfWorkbook, String tableName) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        hssfWorkbook.write(baos);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + tableName);
        return new ResponseEntity(baos.toByteArray(), headers, HttpStatus.OK);
    }


    public static <T> List<T> getEntityList(MultipartFile file, int sheetNum, Class clazz) throws IOException, IllegalAccessException {
        return getEntityList(file, sheetNum, clazz, (List)null);
    }

    public static <T> List<T> getEntityList(MultipartFile file, int sheetNum, Class clazz, List<ExtendColumnInfo> extendColumnInfos) throws IOException, IllegalAccessException {
        Field field = null;
        Workbook wb = getWorkbook(file);
        Sheet sheet = wb.getSheetAt(sheetNum);
        DataFormatter formatter = new DataFormatter();
        if (sheet == null) {
            throw new BaseKnownException(500,"没有工作sheet");
        } else {
            List<String> properties = getProrpertys(sheet);
            Field[] fields = ReflectUtil.getAllFields(clazz);
            Field[] var11 = fields;
            int var12 = fields.length;

            int r;
            for(r = 0; r < var12; ++r) {
                Field f = var11[r];
                f.setAccessible(true);
            }

            List<T> entityList = new ArrayList();

            for(r = 2; r <= sheet.getLastRowNum(); ++r) {
                Row row = sheet.getRow(r);
                if (row != null) {
                    T entity = (T) BeanUtils.instantiateClass(clazz);
                    IExtendObject iExtendObject = null;
                    if (entity instanceof IExtendObject) {
                        iExtendObject = (IExtendObject)entity;
                        iExtendObject.setExtend(new HashMap(IntegerConst.MAP_SIZE));
                    }

                    for(int i = 0; i < properties.size(); ++i) {
                        Field[] var17 = fields;
                        int var18 = fields.length;

                        for(int var19 = 0; var19 < var18; ++var19) {
                            Field f = var17[var19];
                            if (f.getName().equals(properties.get(i))) {
                                field = f;
                                break;
                            }
                        }

                        Cell cell;
                        String cellString;
                        if (field != null) {
                            cell = row.getCell(i);
                            if (cell != null) {
                                cellString = formatter.formatCellValue(cell);
                                field.set(entity, StringObjectMapper.convertStringToObject(field, cellString));
                            }

                            field = null;
                        }

                        if (iExtendObject != null) {
                            cell = row.getCell(i);
                            if (cell != null) {
                                cellString = formatter.formatCellValue(cell);
                                ExtendColumnInfo cellPropertyExtendInfo = null;
                                Iterator var26 = extendColumnInfos.iterator();

                                while(var26.hasNext()) {
                                    ExtendColumnInfo extendColumnInfo = (ExtendColumnInfo)var26.next();
                                    if (extendColumnInfo.getColumnName().equals(properties.get(i))) {
                                        cellPropertyExtendInfo = extendColumnInfo;
                                        break;
                                    }
                                }

                                if (cellPropertyExtendInfo != null) {
                                    Object value = StringObjectMapper.getExtendObject(cellString, cellPropertyExtendInfo);
                                    iExtendObject.getExtend().put(properties.get(i), value);
                                }
                            }
                        }
                    }

                    entityList.add(entity);
                }
            }

            return entityList;
        }
    }


    private static List<String> getProrpertys(Sheet sheet) {
        List<String> properties = new ArrayList();
        Row row = sheet.getRow(1);

        for(int i = 0; i < row.getLastCellNum(); ++i) {
            properties.add(row.getCell(i).getStringCellValue());
        }

        return properties;
    }

    private static Workbook getWorkbook(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        InputStream is = file.getInputStream();
        Object wb;
        if (fileName.matches("^.+\\.(?i)(xls)$")) {
            wb = new HSSFWorkbook(is);
        } else {
            if (!fileName.matches("^.+\\.(?i)(xlsx)$")) {
                throw new BaseKnownException(500, "execl失败");
            }

            wb = new HSSFWorkbook(is);
        }

        return (Workbook)wb;
    }
}
