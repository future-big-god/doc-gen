package com.dev.tools.kit.parser;

import com.dev.tools.kit.domain.MethodInfo;
import com.dev.tools.kit.source.LocalProjectSourceReader;
import com.dev.tools.kit.source.SourceReader;
import com.sun.javadoc.*;

/**
 * java doc api 提供的方法解析
 * @author zhoujun
 */
public class JavaDocMethodParser implements  MethodParser{

    private static RootDoc rootDoc;

    private String srcRootPath;

    private SourceReader sourceReader;

    public static  class Doclet {
        public static boolean start(RootDoc rootDoc) {
            JavaDocMethodParser.rootDoc = rootDoc;
            return true;
        }
        public static LanguageVersion languageVersion() {
            return LanguageVersion.JAVA_1_5;
        }
    }


    public JavaDocMethodParser(String srcRootPath) {

        this.srcRootPath=srcRootPath;
        this.sourceReader = new LocalProjectSourceReader(srcRootPath);
    }


    @Override
    public MethodInfo parse(String methodId) {
        String interfaceName = methodId.split("#")[0];
        String methodName = methodId.split("#")[1];
        String sourcePath=this.sourceReader.getSourceLocation(interfaceName);
        com.sun.tools.javadoc.Main.execute(new String[] {"-doclet",
                JavaDocMethodParser.Doclet.class.getName(),
                "-encoding","utf-8","-sourcepath",
                this.srcRootPath,
                sourcePath});

        MethodDoc methodDoc=buildMethodDoc(interfaceName,methodName);
        return extractMethodInfo(interfaceName, methodName,methodDoc);
    }

    private MethodDoc buildMethodDoc(String interfaceName, String methodName) {
        ClassDoc classDoc=rootDoc.classNamed(interfaceName);
        MethodDoc[] methodDocs=classDoc.methods();
        for(MethodDoc methodDoc:methodDocs){
            if(methodDoc.name().equals(methodName)){
                return methodDoc;
            }
        }
       throw new RuntimeException("没有找到该方法的文档注释");
    }

    private MethodInfo extractMethodInfo(String interfaceName, String methodName,MethodDoc methodDoc) {
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setInterfaceName(interfaceName);
        methodInfo.setMethodName(methodName);
        //方法的注释
        methodInfo.setDesc(methodDoc.commentText());
        //方法参数处理
         JavaDocParamArgusParser paramArgusParser = new JavaDocParamArgusParser();
         paramArgusParser.parse(methodDoc.parameters(),methodDoc.getRawCommentText());
         methodInfo.setParamInfo(paramArgusParser.getParamInfo());
        //方法返回值处理
        JavaDocReturnInfoParser returnInfoParser = new JavaDocReturnInfoParser();
        returnInfoParser.parse(methodDoc.returnType());
        methodInfo.setReturnInfoList(returnInfoParser.getReturnModels());
        return methodInfo;
    }

}
