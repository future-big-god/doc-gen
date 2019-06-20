package com.dev.tools.kit.parser;

import com.dev.tools.kit.domain.MethodInfo;
import com.dev.tools.kit.source.LocalProjectSourceReader;
import com.dev.tools.kit.source.SourceReader;

import java.util.List;

/**
 * @Description:默认方法解析器
 * @Author: zhangjianfeng
 * @Date: 2018-09-25
 */
public class DefaultMethodParser implements MethodParser {

    private SourceReader sourceReader;

    private String srcRootPath;

    public DefaultMethodParser(String srcRootPath) {
        this.srcRootPath = srcRootPath;
        this.sourceReader = new LocalProjectSourceReader(srcRootPath);
    }

    @Override
    public MethodInfo parse(String methodId) {
        String interfaceName = methodId.split("#")[0];
        String methodName = methodId.split("#")[1];
        String sourceContent = this.sourceReader.readContent(interfaceName);
        MethodLinesParser methodLinesParser = new MethodLinesParser();
        methodLinesParser.parse(sourceContent, methodName);
        if (methodLinesParser.getMethodLine() == null) {
            throw new RuntimeException("can`t extract method in interface,please check the method format");
        }
        ImportLinesParser importLinesParser = new ImportLinesParser();
        importLinesParser.parse(sourceContent);
        return extractMethodInfo(interfaceName, methodName, methodLinesParser.getMethodLine(), methodLinesParser.getCommentLines(), importLinesParser.getLines());
    }


    private MethodInfo extractMethodInfo(String interfaceName, String methodName, String methodLine, List<String> commentLines, List<String> importLines) {
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setInterfaceName(interfaceName);
        methodInfo.setMethodName(methodName);
        methodInfo.setDesc(ParseUtils.genDesc(commentLines));
        ParamArgusParser paramArgusParser = new ParamArgusParser();
        paramArgusParser.parse(interfaceName, methodLine, commentLines, importLines, srcRootPath);
        methodInfo.setParamInfo(paramArgusParser.getParamInfo());
        ReturnInfoParser returnInfoParser = new ReturnInfoParser();
        returnInfoParser.parse(interfaceName, methodLine, importLines, srcRootPath);
        methodInfo.setReturnInfoList(returnInfoParser.getReturnModels());
        return methodInfo;
    }

}
