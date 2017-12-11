package com.algoq.algoq.services;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;


@Service
public class PDFService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private String tmpPath = "/Users/quibbleh4ck/tmp/";
    private String htmlPath = "/Users/quibbleh4ck/tmp/test.html";
    private String stylePath = "/Users/quibbleh4ck/tmp/styles.css";
    private String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date());

    /**
     * Creates a custom PDF for the specific question to send
     */
    public void generatePDF() throws DocumentException, IOException {
        log.info("Constructing new PDF Document");
        Document doc = new Document();
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(tmpPath + timeStamp + ".pdf"));
        doc.open();


        //Insert PDF Generation Logic Here



        doc.close();
        log.info("PDF Generated");
    }
}
