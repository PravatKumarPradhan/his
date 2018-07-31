package com.param.lis.global.common;

import java.io.FileInputStream;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class ZonedDate 


{
  public static void main(String[] args) {
    try {
          FileInputStream fis = new FileInputStream("C:\\Users\\ADMIN\\Downloads\\formal_report_template.doc");
          XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
          XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
          System.out.println(extractor.getText());
          extractor.close();
       } catch(Exception ex) {
           ex.printStackTrace();
       }
}
  }