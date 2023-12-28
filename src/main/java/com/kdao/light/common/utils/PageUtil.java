package com.kdao.light.common.utils;

import com.github.pagehelper.PageHelper;
import com.kdao.light.common.dto.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * <p>Title: light</p >
 * <p>Description: PageUtil</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2023-05-17 18:41 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
public class PageUtil {
    public static void main(String[] args) {
        Function<String, String> function = (s) -> s;
        System.out.println(function.apply("123"));
    }

    public static <P extends PageInfo, R> Page getPage(Function<P, List<R>> function, P param) {
        return getPage(function, param, null);
    }

    public static <P extends PageInfo, I, R> Page getPage(Function<P, List<I>> function, P param, Class<R> resultClass) {
        PageHelper.startPage(param.getRequest().getPageNumber() + 1, param.getRequest().getPageSize());
        if (param.getRequest().getSort().isSorted()) {
            StringBuilder orderBy = new StringBuilder();
            for (Iterator<Sort.Order> iterator = param.getRequest().getSort().iterator(); iterator.hasNext(); orderBy.append(" ")) {
                Sort.Order next = (Sort.Order) iterator.next();
                String property = next.getProperty();
                orderBy.append(property);
                orderBy.append(" ");
                if (next.getDirection().isAscending()) {
                    orderBy.append("ASC");
                } else {
                    orderBy.append("DESC");
                }
            }
            PageHelper.orderBy(orderBy.toString());
        }

        List<I> result = function.apply(param);
        com.github.pagehelper.PageInfo<I> pageInfo = new com.github.pagehelper.PageInfo<>(result);
        PageImpl page = new PageImpl(result, param.getRequest(), pageInfo.getTotal());
        return (Page) (resultClass != null ? DtoMapper.convertPage(page, resultClass, new String[0]) : page);
    }
}
