package com.xstore.partner;


import org.junit.Test;

import java.util.Properties;

public class CommonTest
{
    @Test
    public void test()
    {
        Properties props=System.getProperties(); //获得系统属性集
        String osName = props.getProperty("os.name"); //操作系统名称
       String osArch = props.getProperty("os.arch"); //操作系统构架
        String osVersion = props.getProperty("os.version"); //操作系统版本
    }
}

