package com.dev.tools.kit;

import com.sun.javadoc.*;

public class ArgDoclet {
    private static RootDoc rootDoc;
    public static  class Doclet {
        public static boolean start(RootDoc rootDoc) {
            ArgDoclet.rootDoc = rootDoc;
            return true;
        }
    }

    /**
     * 显示DocRoot中的基本信息
     */
    public static void show(){
        ClassDoc[] classes = rootDoc.classes();
        for(ClassDoc classDoc : classes){
            System.out.println(classDoc.fields());
        }
    }
}
