package com.dev.tools.kit.confluence;

import com.dev.tools.kit.DocGenerator;
import com.dev.tools.kit.parser.DefaultMethodParser;
import com.dev.tools.kit.parser.MethodParser;
import com.dev.tools.kit.DocWriter;


/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-25
 */
public class ConfluenceDocGenerator implements DocGenerator {

    private MethodParser methodParser;
    private DocWriter docWriter;

    public ConfluenceDocGenerator(String projectRootPath) {
        this.methodParser = new DefaultMethodParser(projectRootPath);
        this.docWriter = new ConfluenceDocWriter();
    }


    @Override
    public void generate(String methodId, String locationId) {
        this.docWriter.write2Cf(locationId,this.methodParser.parse(methodId));
    }

    @Override
    public void batchGenerate(String interfaceName, String[] methodIds, String locationId) {
        if(methodIds==null || methodIds.length<=0){
            return ;
        }
        for(String methodId:methodIds){
            this.generate(interfaceName+"#"+methodId,locationId);
        }
    }

    @Override
    public void write2Console(String methodId) {
        this.docWriter.write2Console(this.methodParser.parse(methodId));
    }
}
