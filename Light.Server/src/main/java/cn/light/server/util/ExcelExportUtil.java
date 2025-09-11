package cn.light.server.util;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class ExcelExportUtil {

    @Resource
    HttpServletResponse response;

    /**
     * 通用分页导出方法
     *
     * @param fileName     文件名
     * @param clazz        DTO类class
     * @param queryDTO     查询条件DTO
     * @param dataProvider 数据提供者函数 ( queryDTO -> PageResult
     * @param <T>          DTO类型
     * @param <Q>          查询DTO类型
     */
    public <T, Q> void export(String fileName,
                              Class<T> clazz, Q queryDTO,
                              Function<Q, List<T>> dataProvider) throws IOException {


        // 设置响应格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String encodedFileName = URLEncoder.encode(fileName + DateUtil.format(new Date(), "yyyyMMddHHmmss"), StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName + ".xlsx");

        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), clazz).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("数据列表").build();

        try {
            // 设置分页参数
            setPageSize(queryDTO);
            setPage(queryDTO, 1);

            List<T> data;
            int total = 0;
            int pageSize = getPageSize(queryDTO);

            do {
                data = dataProvider.apply(queryDTO);
                if (!data.isEmpty()) {
                    excelWriter.write(data, writeSheet);
                }
                setPage(queryDTO, getPage(queryDTO) + 1);
                total += data.size();

                // 最多导出20万条数据
                if (total >= 200000) {
                    break;
                }
            } while (data.size() == pageSize);

        } catch (Exception e) {
            throw new RuntimeException("导出Excel失败", e);
        } finally {
            excelWriter.finish();
        }
    }

    // 反射设置分页大小
    private static void setPageSize(Object queryDTO) {
        try {
            java.lang.reflect.Method method = queryDTO.getClass().getMethod("setPageSize", int.class);
            method.invoke(queryDTO, 5000);
        } catch (Exception e) {
            throw new RuntimeException("无法设置分页大小", e);
        }
    }

    // 反射设置页码
    private static void setPage(Object queryDTO, int page) {
        try {
            java.lang.reflect.Method method = queryDTO.getClass().getMethod("setPage", int.class);
            method.invoke(queryDTO, page);
        } catch (Exception e) {
            throw new RuntimeException("无法设置页码", e);
        }
    }

    // 反射获取分页大小
    private static int getPageSize(Object queryDTO) {
        try {
            java.lang.reflect.Method method = queryDTO.getClass().getMethod("getPageSize");
            return (int) method.invoke(queryDTO);
        } catch (Exception e) {
            throw new RuntimeException("无法获取分页大小", e);
        }
    }

    // 反射获取页码
    private static int getPage(Object queryDTO) {
        try {
            java.lang.reflect.Method method = queryDTO.getClass().getMethod("getPage");
            return (int) method.invoke(queryDTO);
        } catch (Exception e) {
            throw new RuntimeException("无法获取页码", e);
        }
    }
}
