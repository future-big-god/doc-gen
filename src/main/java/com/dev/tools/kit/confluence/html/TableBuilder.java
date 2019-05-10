package com.dev.tools.kit.confluence.html;

import com.dev.tools.kit.domain.TableInfo;
import com.dev.tools.kit.domain.TableRow;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;


/**
 * @Description:
 * @Author: zhangjianfeng
 * @Date: 2018-09-26
 */
public class TableBuilder {
    private TableHeaderBuilder tableHeaderBuilder;
    private TableTitleRowBuilder tableTitleRowBuilder;
    private TableContentRowsBuilder tableContentRowsBuilder;

    public TableBuilder(){
         tableHeaderBuilder=new TableHeaderBuilder();
         tableTitleRowBuilder=new TableTitleRowBuilder();
         tableContentRowsBuilder=new TableContentRowsBuilder();
    }
    public String build(TableInfo tableInfo) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<table class=\"confluenceTable\"><tbody>");
        stringBuilder.append(tableHeaderBuilder.build(tableInfo.getHeader()));
        stringBuilder.append(tableTitleRowBuilder.build(tableInfo.getTitleRow()));
        stringBuilder.append(tableContentRowsBuilder.build(tableInfo.getContentRows()));
        stringBuilder.append("</tbody></table>");
        return stringBuilder.toString();
    }
    class TableHeaderBuilder{
        protected String  build(String text){
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("<tr><th class=\"confluenceTh\" colspan=\"4\">").append(StringEscapeUtils.escapeHtml4(text))
                         .append("</th></tr>");
            return stringBuilder.toString();
        }
    }
    class  TableTitleRowBuilder{
        protected String  build(TableRow tableRow){
            if (tableRow == null)
            {
                return "";
            }
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("<tr>");
            for(String value:tableRow.getValues()){
                stringBuilder.append("<td class=\"confluenceTd\">");
                stringBuilder.append(StringEscapeUtils.escapeHtml4(value));
                stringBuilder.append("</td>");
            }
            stringBuilder.append("</tr>");
            return stringBuilder.toString();
        }
    }
    class  TableContentRowsBuilder{
        protected String build(List<TableRow> tableRows){
            if (tableRows == null)
            {
                return "";
            }
            StringBuilder stringBuilder=new StringBuilder();
            for(TableRow tableRow:tableRows){
                stringBuilder.append(tableTitleRowBuilder.build(tableRow));
            }
            return stringBuilder.toString();
        }
    }
}
