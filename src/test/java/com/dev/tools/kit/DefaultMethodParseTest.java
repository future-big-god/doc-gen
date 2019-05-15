package com.dev.tools.kit;


import com.dev.tools.kit.domain.MethodInfo;
import com.dev.tools.kit.parser.DefaultMethodParser;
import com.dev.tools.kit.parser.MethodParser;
import org.junit.Assert;
import org.junit.Test;

public class DefaultMethodParseTest {
    @Test
    public void test() {
        MethodParser methodParser = new DefaultMethodParser("/Users/zhangjianfeng5/IdeaProjects/shelf/xstore-shelf-space-center/xstore-shelf-space-api/src/main/java/");
        MethodInfo methodInfo = methodParser.parse("com.xstore.shelf.space.api.category.CategoryService#getCategoryGeneral");
        Assert.assertNotNull(methodInfo);
        System.out.println(methodInfo);
    }
}

