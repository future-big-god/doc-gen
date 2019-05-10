package com.dev.tools.kit.confluence;

import com.dev.tools.kit.DocWriter;
import com.dev.tools.kit.confluence.html.ConfluenceHtmlBuilder;
import com.dev.tools.kit.confluence.html.HtmlBuilder;
import com.dev.tools.kit.domain.GetResult;
import com.dev.tools.kit.domain.MethodInfo;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-25
 */
public class ConfluenceDocWriter implements DocWriter {
    private HtmlBuilder htmlBuilder;
    private ConfluenceClient confluenceClient;

    public ConfluenceDocWriter() {
        this.htmlBuilder = new ConfluenceHtmlBuilder();
        this.confluenceClient = new DefaultConfluenceClient();
    }

    @Override
    public void write2Cf(String locationId, MethodInfo methodInfo) {

        String pageTitle=methodInfo.getDesc();
        String pageHtml=htmlBuilder.buildHtml(methodInfo);
        GetResult getResult= confluenceClient.getPageByTitle("7fresh",pageTitle);
        if(getResult.getSize()>0){
            confluenceClient.updatePage(getResult.getResults().get(0).getId(),methodInfo.getDesc(),pageHtml);
        }
        else{
            System.out.println(pageHtml);
            confluenceClient.createPage(locationId,methodInfo.getDesc(),pageHtml);
        }
    }

    @Override
    public void write2Console(MethodInfo methodInfo) {
        String html = htmlBuilder.buildHtml(methodInfo);
        setSysClipboardText(html);
        System.out.println("接口定义生成的html如下：");
        System.out.println();
        System.out.println(html);
        System.out.println();
        System.out.println("html已经复制到粘贴板，去CF粘贴吧~~");
    }

    private  void setSysClipboardText(String writeMe) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(writeMe);
        clip.setContents(tText, null);
    }

}
