package cn.light.generator.config;

/**
 * Config
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2024/8/5 7:51
 */

import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.ds.GlobalDSFactory;
import cn.hutool.setting.Setting;

  public class Config {
      public static void  initialization() {
          String env = System.getenv("env");
          //获取当前路径
          Setting setting = new Setting("db-" + env + ".setting");
          Const.TABLE_PREFIX =  setting.get("db-prefix");
          Const.ROOT_PATH =   setting.get("path-base");
          Const.VUE_PATH =   setting.get("path-vue");
          //移除db不认的部分 避免影响
          setting.remove("path-base");
          setting.remove("path-vue");
          setting.remove("db-prefix");

          GlobalDSFactory.set(DSFactory.create(setting));
      }
  }