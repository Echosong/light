package com.kdao.light.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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

    public PageInfo() {
    }

    public PageInfo(int page, int pageSize, boolean sort, String sortCol) {
        this.page = page;
        this.pageSize = pageSize;
        this.sort = sort;
        this.sortCol = sortCol;
    }

    @JsonIgnore
    public PageRequest getRequest() {
        return this.sort ? PageRequest.of(this.page -1, this.pageSize, Sort.by(this.direction ? Sort.Direction.ASC : Sort.Direction.DESC, new String[]{this.sortCol})) : PageRequest.of(this.page, this.pageSize);
    }
}
