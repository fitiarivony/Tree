/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

/**
 *
 * @author andri
 */
import com.itextpdf.html2pdf.HtmlConverter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class ScMarker {

    String model;
    Map<String, Object> data = new HashMap<>();
    static Configuration configuration;

    public ScMarker(String model) {
        this.setModel(model);
    }

    public ScMarker() {
    }

    static {
        Configuration cf = new Configuration(Configuration.VERSION_2_3_31);
        cf.setClassForTemplateLoading(ScMarker.class, "/templates");
        ScMarker.configuration = cf;
    }

    public static Template getTemplate(String model)
            throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
        return ScMarker.configuration.getTemplate(model);
    }

    public void clear() {
        this.setData(new HashMap<>());
    }

    public void put(String key, Object data) {
        this.getData().put(key, data);
    }

    public String changeContent(String model, Map<String, Object> dataModel)
            throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
            TemplateException {
        this.setModel(model);
        this.setData(dataModel);
        return this.getContent();
    }

    public String changeContent(Map<String, Object> dataModel)
            throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
            TemplateException {
        return this.changeContent(this.getModel(), dataModel);
    }

    public String changeContent(Template template, Map<String, Object> dataModel)
            throws TemplateException, IOException {
        StringWriter stringWriter = new StringWriter();
        template.process(dataModel, stringWriter);
        return stringWriter.toString();
    }

    public String getContent() throws TemplateException, IOException {
        Template template = ScMarker.getTemplate(this.getModel());
        return this.changeContent(template, this.getData());
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(Configuration configuration) {
        ScMarker.configuration = configuration;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public  byte[] toPdf(String pdfPath) throws IOException, TemplateException {
        try (FileOutputStream pdf = new FileOutputStream(pdfPath)) {
            HtmlConverter.convertToPdf(this.getContent(), pdf);
        }
        return Files.readAllBytes(Paths.get(pdfPath));
    }
}
