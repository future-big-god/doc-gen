package com.xstore.partner.doc.cf;

import com.xstore.partner.doc.DocGenerator;
import com.xstore.partner.doc.parser.DefaultMethodParser;
import com.xstore.partner.doc.parser.MethodParser;
import com.xstore.partner.doc.DocWriter;


/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-25
 */
public class CFDocGenerator implements DocGenerator {

    private MethodParser methodParser;
    private DocWriter docWriter;

    public CFDocGenerator(String projectRootPath) {
        this.methodParser = new DefaultMethodParser(projectRootPath);
        this.docWriter = new CFDocWriter();
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
