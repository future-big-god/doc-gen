package com.xstore.partner.doc.cf;

import com.xstore.partner.doc.DocWriter;
import com.xstore.partner.doc.cf.html.CFHtmlBuilder;
import com.xstore.partner.doc.cf.html.HtmlBuilder;
import com.xstore.partner.doc.domain.GetResult;
import com.xstore.partner.doc.domain.MethodInfo;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-25
 */
public class CFDocWriter implements DocWriter {
    private HtmlBuilder htmlBuilder;
    private CFClient cfClient;

    public CFDocWriter() {
        this.htmlBuilder = new CFHtmlBuilder();
        this.cfClient = new DefaultCFClient();
    }

    @Override
    public void write2Cf(String locationId, MethodInfo methodInfo) {

        String pageTitle=methodInfo.getDesc();
        String pageHtml=htmlBuilder.buildHtml(methodInfo);
        GetResult getResult=cfClient.getPageByTitle("7fresh",pageTitle);
        if(getResult.getSize()>0){
            cfClient.updatePage(getResult.getResults().get(0).getId(),methodInfo.getDesc(),pageHtml);
        }
        else{
            System.out.println(pageHtml);
            cfClient.createPage(locationId,methodInfo.getDesc(),pageHtml);
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
