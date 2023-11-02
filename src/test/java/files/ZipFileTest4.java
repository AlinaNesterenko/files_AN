package filesProject;

import model.Fruit;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZipFileTest4 {

  ClassLoader cl = ZipFileTest4.class.getClassLoader();

  @Test
  void zipTestPdf() throws Exception {
    try (InputStream stream = cl.getResourceAsStream("filesForTest.zip");
         ZipInputStream zis = new ZipInputStream(stream)) {

      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        final String name = entry.getName();
        System.out.println(entry.getName());
        if (name.contains(".pdf")) {
          PDF pdf = new PDF(zis);
          assertThat("sum").isEqualTo(pdf.title);
          System.out.println("pdf - ok!");
        }

      }
    }
  }

  @Test
  void zipTestXLS() throws Exception {
    try (InputStream stream = cl.getResourceAsStream("filesForTest.zip");
         ZipInputStream zis = new ZipInputStream(stream)) {

      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        final String name = entry.getName();
        if (name.contains(".xlsx")) {
          XLS xls = new XLS(zis);
          assertEquals("qRCode",
              xls.excel.getSheetAt(0)
                  .getRow(0)
                  .getCell(1)
                  .getStringCellValue());
          System.out.println("xls - ok!");
        }
      }
    }
  }

  @Test
  void zipTestCSV() throws Exception {
    try (InputStream stream = cl.getResourceAsStream("filesForTest.zip");
         ZipInputStream zis = new ZipInputStream(stream)) {

      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        final String name = entry.getName();
        if (name.contains(".csv")) {
          CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
          List<String[]> content = csvReader.readAll();
          assertEquals(5, content.size());

          final String[] firstRow = content.get(0);
          final String[] secondRow = content.get(1);
          final String[] thirdRow = content.get(2);

          Assertions.assertArrayEquals(new String[] {"\uFEFFQRCode;Type;Size;Weight"}, firstRow);
          Assertions.assertArrayEquals(new String[] {"1;Apple;12;23"}, secondRow);
          Assertions.assertArrayEquals(new String[] {"2;Orange;98;98"}, thirdRow);
          System.out.println("csv - ok!");
        }
      }
    }
  }


}