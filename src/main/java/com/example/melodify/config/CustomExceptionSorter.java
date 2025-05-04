package com.example.melodify.config;

import com.alibaba.druid.pool.ExceptionSorter;

import java.sql.SQLException;
import java.util.Properties;

class CustomExceptionSorter implements ExceptionSorter {

  @Override
  public boolean isExceptionFatal(SQLException e) {
    // 将所有异常视为致命异常，即抛出到上层
    // 打印异常堆栈信息
    e.printStackTrace();
    return true;
  }

  @Override
  public void configFromProperties(Properties properties) {
    // 配置信息可以为空
  }
}
