package com.kdao.light.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.digest.MD5;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class HomeTest {

    private Date getLastWeekMonday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        //周一
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    @Test
    public void  index(){

        Console.log( MD5.create().digestHex("123456"));
        Console.log( SmUtil.sm3().digestHex("123456"));

        Console.log(DateUtil.lastWeek());
        Console.log(getLastWeekMonday());
    }


}
