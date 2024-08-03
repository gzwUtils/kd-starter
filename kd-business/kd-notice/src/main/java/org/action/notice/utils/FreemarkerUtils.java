package org.action.notice.utils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author gzw
 * @description： freemarker
 * @since：2022/11/5 16:59
 */
@Component
@SuppressWarnings("all")
@Slf4j
public class FreemarkerUtils {


    public Configuration configuration(String freemarkerTemplate, String templateId) {
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate(templateId, freemarkerTemplate);
        Configuration configuration = new Configuration();
        configuration.setTemplateLoader(stringLoader);
        return configuration;
    }

    /**
     * freemarker渲染 template
     */
    public  String freeMarkerRender(Map<String, String> data, String template,String templateId) {
        Writer out = new StringWriter();
        try {
            // 获取模板,并设置编码方式
            Template templateTemp = configuration(template, templateId).getTemplate(templateId);
            templateTemp.setEncoding("UTF-8");
            // 合并数据模型与模板
            templateTemp.process(data, out);
            out.flush();
            return out.toString();
        } catch (Exception e) {
            log.error("freeMaker异常 message:{}", e.getMessage(), e);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                log.error("freeMaker异常 message:{}", ex.getMessage(), ex);
            }
        }
        return null;
    }

}
