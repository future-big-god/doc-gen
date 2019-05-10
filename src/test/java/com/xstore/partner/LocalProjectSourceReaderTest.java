package com.xstore.partner;


import com.xstore.partner.doc.source.LocalProjectSourceReader;
import com.xstore.partner.doc.source.SourceReader;
import org.junit.Test;

public class LocalProjectSourceReaderTest
{
    @Test
    public void test()
    {
        SourceReader sourceReader = new LocalProjectSourceReader("/Users/zhangjianfeng5/IdeaProjects/xstore-partner-center-api");
        System.out.println(sourceReader.readContent("com.jd.xstore.partner.center.api.supplierproduct.SupplierProductService"));
    }
}

