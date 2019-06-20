package com.dev.tools.kit;


import com.dev.tools.kit.source.LocalProjectSourceReader;
import com.dev.tools.kit.source.SourceReader;
import org.junit.Assert;
import org.junit.Test;

public class LocalProjectSourceReaderTest {
    @Test
    public void test() {
        SourceReader sourceReader = new LocalProjectSourceReader("/Users/zhangjianfeng5/IdeaProjects/shelf/xstore-shelf-space-center/xstore-shelf-space-api/src/main/java/");
        String srcContent = sourceReader.readContent("com.xstore.shelf.space.api.category.CategoryService");
        Assert.assertNotNull(srcContent);
        System.out.println(srcContent);
    }
}

