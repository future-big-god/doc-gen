package com.dev.tools.kit;

import com.sun.javadoc.*;

import java.util.ArrayList;
import java.util.List;

public class DocTest extends Doclet {
    private static RootDoc rootDoc;
    public static  class Doclet {
        public static boolean start(RootDoc rootDoc) {
            DocTest.rootDoc = rootDoc;
            return true;
        }
    }

    /**
     * 显示DocRoot中的基本信息
     */
    public static void show(){
        ClassDoc[] classes = rootDoc.classes();
        for(ClassDoc classDoc : classes){

            System.out.println(classDoc.name()+
                    "类的注释:"+classDoc.commentText());
            MethodDoc[] methodDocs = classDoc.methods();
            for(MethodDoc methodDoc : methodDocs){
                // 打印出方法上的注释
                //处理参数
                Parameter[] parameters=methodDoc.parameters();
                for(int i=0;i<parameters.length;i++){
                    if(parameters[i].type().isPrimitive()){
                        System.out.println("method："+methodDoc.name()+"参数是"+parameters[i].typeName());
                        continue;
                    }
                    //继续加载
                    cccc(parameters[i].type());

                }
                System.out.println(getGenTypeNames(methodDoc.returnType()));

                System.out.println("类"
                        +classDoc.name()+","
                        +methodDoc.name()+
                        "方法注释:"
                        +methodDoc.commentText());
            }
        }
    }
    static void cccc(Type type){
        System.out.println(type.getClass().getResource("/").getPath());
        ClassDoc classDoc=type.asClassDoc();
        FieldDoc[] fieldDocs=classDoc.fields(false);
        for(FieldDoc fieldDoc:fieldDocs){
            System.out.println(fieldDoc.commentText());
        }

        /*com.sun.tools.javadoc.Main.execute(new String[] {"-doclet",
                ArgDoclet.class.getName(),
                "-encoding","utf-8","-classpath",
                "/Users/zhoujun/Documents/project/imis-studio/imis-studio-web/target/imis-studio-web-0.0.1-SNAPSHOT/WEB-INF/lib/imis-studio-service-0.0.1-SNAPSHOT.jar",
                "/Users/zhoujun/Documents/project/imis-studio/imis-studio-service/src/main/java/com/cqupt/imis/service/UserService.java"});
        ArgDoclet.show();*/

    }
    static List<String> getGenTypeNames(Type typ) {

        String jarFilePath = typ.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
        System.out.println(jarFilePath);
        if (typ instanceof ParameterizedType) {
            List<String> ret = new ArrayList<String>();
            ParameterizedType pt = typ.asParameterizedType();
            Type[] typeArgs = pt.typeArguments();
            for (Type t : typeArgs) {
                ret.add(t.typeName());
            }
            return ret;
        }
        return null;
    }


    public static void main(String[] args) {
        com.sun.tools.javadoc.Main.execute(new String[] {"-doclet",
                Doclet.class.getName(),
                "-encoding","utf-8","-sourcepath",
                "/Users/zhoujun/Documents/project/imis-studio/imis-studio-service/src/main/java",
                "/Users/zhoujun/Documents/project/imis-studio/imis-studio-service/src/main/java/com/cqupt/imis/service/UserService.java"});
        show();
    }
}
