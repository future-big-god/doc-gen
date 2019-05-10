package com.xstore.partner;


import com.xstore.partner.doc.parser.DefaultMethodParser;
import com.xstore.partner.doc.parser.MethodParser;
import org.junit.Test;

public class DefaultMethodParseTest
{
    @Test
    public void test()
    {
        MethodParser methodParser = new DefaultMethodParser("/project/xstore-partner-center-api");
        methodParser.parse("com.jd.xstore.partner.center.api.supplierproduct.SupplierProductService#findSupplierProductsByStoreIdAndProductId");
    }
}

