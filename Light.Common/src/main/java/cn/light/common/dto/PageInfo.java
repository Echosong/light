package cn.light.common.dto;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:02</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class PageInfo {
    @Schema(defaultValue = "页数，默认0,第一页")
    private int page = 1;
    @Schema(defaultValue = "每页个数,默认10")
    private int pageSize = 10;
    @Schema(defaultValue = "是否排序,默认不排序")
    private boolean sort = true;
    @Schema(defaultValue = "是否正序,默认正序")
    private boolean direction = false;
    @Schema(defaultValue = "排序列名")
    private String sortCol = "id";

    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            pageSize = 1;
        }
        if (pageSize > 1000) {
            pageSize = 1000;
        }
        this.pageSize = pageSize;
    }

    private List<OrderBy> orderByList;

    @Getter
    @Setter
    @Builder
    public static class OrderBy {
        private String column;
        private boolean direction;
    }

    public PageInfo() {
    }


    public PageInfo(int page, int pageSize, boolean sort, String sortCol) {
        this.page = page;
        this.pageSize = pageSize;
        this.sort = sort;
        //注意，这里需要去掉Enum 结尾的
        this.sortCol = StrUtil.subBefore(sortCol, "Enum", true);
    }

    @JsonIgnore
    public PageRequest getRequest() {
        this.sortCol = StrUtil.subBefore(sortCol, "Enum", true);
        if (CollUtil.isNotEmpty(orderByList)) {
            if (this.sort) {
                Sort sort = Sort.unsorted();
                int i = 0;
                for (OrderBy orderBy : orderByList) {
                    sort = sort.and(Sort.by(orderBy.direction ? Sort.Direction.ASC : Sort.Direction.DESC, i == 0 ? orderBy.column : ("," + orderBy.column)));
                    i = i + 1;
                }
                return PageRequest.of(this.page - 1, this.pageSize, sort);
            } else {
                return PageRequest.of(this.page, this.pageSize);
            }
        } else {
            return this.sort ? PageRequest.of(this.page - 1, this.pageSize, Sort.by(this.direction ? Sort.Direction.ASC : Sort.Direction.DESC, new String[]{this.sortCol})) : PageRequest.of(this.page, this.pageSize);
        }
    }
}
