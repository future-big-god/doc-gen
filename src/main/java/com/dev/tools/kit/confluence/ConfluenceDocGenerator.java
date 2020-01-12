package com.dev.tools.kit.confluence;

import com.dev.tools.kit.DocContentBuilder;
import com.dev.tools.kit.DocGenerator;
import com.dev.tools.kit.DocWriter;
import com.dev.tools.kit.confluence.html.ConfluenceDocContentBuilder;
import com.dev.tools.kit.domain.DocInfo;
import com.dev.tools.kit.parser.DefaultMethodParser;
import com.dev.tools.kit.parser.MethodParser;


/**
 * @Description:Confluence文档生成器
 * @Author: zhangjianfeng
 * @Date: 2018-09-25
 */
public class ConfluenceDocGenerator implements DocGenerator {

    private MethodParser methodParser;
    private DocWriter docWriter;
    private DocContentBuilder docContentBuilder;

    public ConfluenceDocGenerator(String srcRootPath) {
        this.methodParser = new DefaultMethodParser(srcRootPath);
        this.docWriter = new ConfluenceDocWriter();
        this.docContentBuilder = new ConfluenceDocContentBuilder();
    }

    public ConfluenceDocGenerator(MethodParser methodParser) {
        this.methodParser = methodParser;
        this.docWriter = new ConfluenceDocWriter();
        this.docContentBuilder = new ConfluenceDocContentBuilder();
    }

    @Override
    public void generateAndWrite(String methodId, String locationId) {
        this.docWriter.write(locationId,
                docContentBuilder.build(
                        this.methodParser.parse(methodId)));
    }

    @Override
    public void batchGenerateAndWrite(String interfaceName, String[] methodIds, String locationId) {
        if (methodIds == null || methodIds.length <= 0) {
            return;
        }
        for (String methodId : methodIds) {
            this.generateAndWrite(interfaceName + "#" + methodId, locationId);
        }
    }

    @Override
    public DocInfo generate(String methodId) {
        return docContentBuilder.build(this.methodParser.parse(methodId));
    }
}
