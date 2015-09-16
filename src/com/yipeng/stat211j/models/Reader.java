package com.yipeng.stat211j.models;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by huangyip on 16/09/2015.
 */
public class Reader {

    public List<String> read(String filePath) {
        List<String> schoolNames = new ArrayList<String>();

        File f = new File(filePath);
        Workbook wb;

        try {
            if (POIXMLDocument.hasOOXMLHeader(new BufferedInputStream(new FileInputStream(filePath)))) {
                wb = new XSSFWorkbook(f);
            } else {
                NPOIFSFileSystem fs = new NPOIFSFileSystem(f);
                wb = new HSSFWorkbook(fs.getRoot(), true);
            }

            // Iterate and parse all sheets
            int nbSheets = wb.getNumberOfSheets();
            for (int i = 0; i < nbSheets; i++) {
                List<String> tmp = parseSheet(wb.getSheetAt(i));
                if (tmp != null) {
                    schoolNames.addAll(tmp);
                }
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return schoolNames;
    }

    private List<String> parseSheet(Sheet ws) {
        if (ws.getPhysicalNumberOfRows() == 0) {
            return null;
        }

        List<String> schoolNames = new ArrayList<String>();
        int lastRowId = ws.getLastRowNum();
        int colId = -1;

        // Iterate reversely the rows to locate the column of school names
        outer:
        for (int i = lastRowId; i >= 0; i--) {
            Row row = ws.getRow(i);
            if (row == null) {
                continue;
            }

            Iterator<Cell> itrCell = row.cellIterator();
            while (itrCell.hasNext()) {
                Cell c = itrCell.next();
                if (c.getCellType() == Cell.CELL_TYPE_STRING) {
                    String val = c.getStringCellValue();
                    if (val.contains("大学") || val.contains("学院")) {
                        colId = c.getColumnIndex();
                        break outer;
                    }
                }
            }
        }

        if (colId == -1) {
            return null;
        }

        // Redo iteration of rows and grab all school names
        Iterator<Row> itrRow = ws.rowIterator();
        while (itrRow.hasNext()) {
            Row row = itrRow.next();
            Cell c = row.getCell(colId);
            if (c != null) {
                String val = clean(c.getStringCellValue());
                if (!val.equals("")) {
                    schoolNames.add(val);
                }
            }
        }

        return schoolNames;
    }

    private String clean(String schoolName) {
        return schoolName.trim().replaceAll("[\\t\\s\\r\\n]", "");
    }
}
