package com.xstore.partner;


import com.dev.tools.kit.source.LocalProjectSourceReader;
import com.dev.tools.kit.source.SourceReader;
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

