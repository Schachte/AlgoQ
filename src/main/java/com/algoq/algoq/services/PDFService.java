package com.algoq.algoq.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.net.FileRetrieve;
import com.itextpdf.tool.xml.net.FileRetrieveImpl;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Service
public class PDFService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    File file = new File("resources/index.html");
    String HTML = file.getAbsolutePath();
    private String DEST = "/Users/quibbleh4ck/tmp/";
//    private String HTML = "/Users/quibbleh4ck/tmp/index.html";
    private String CSS = "/Users/quibbleh4ck/tmp/";
    private String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date());

    /**
     * Creates a custom PDF for the specific question to send
     */
    public void generatePDF() throws DocumentException, IOException {
        log.info("Constructing new PDF Document");
        // step 1
        Document document = new Document();

        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/tmp/" + timeStamp + ".pdf"));
        writer.setInitialLeading(12.5f);
        // step 3
        document.open();

        // CSS
        CSSResolver cssResolver =
                XMLWorkerHelper.getInstance().getDefaultCssResolver(false);
        FileRetrieve retrieve = new FileRetrieveImpl(CSS);
        cssResolver.setFileRetrieve(retrieve);

        // HTML
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        htmlContext.autoBookmark(false);
//        htmlContext.setImageProvider(new AbstractImageProvider() {
//            public String getImageRootPath() {
//                return "/tmp/images";
//            }
//        });

        // Pipelines
        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

        // XML Worker
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);
        p.parse(new FileInputStream(HTML));

        // step 5
        document.close();
    }
}
