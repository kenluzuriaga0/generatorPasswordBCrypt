package com.example.nek;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
    private String nombreArchivo;
    private String rutaArchivo;

    private static final Logger LOGGER = Logger.getLogger(ReadExcel.class.getName());

    public ReadExcel(String rutaArchivo, String nombreArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.nombreArchivo = nombreArchivo;
    }
 /*for(int RowNum=0; RowNum<MaxArrayLength;RowNum++){
        HSSFRow row = sheet.createRow(RowNum);
        for(int ColNum=0; ColNum<ArrayWidth;ColNum++){
            HSSFCell cell = row.createCell(ColNum);
            cell.setCellValue(ArrayList[RowNum][ColNum]);
        }
    }*/

    public void writeExcelFile(ArrayList<ArrayList<String>> row_list) {
        FileInputStream file = null;
        XSSFWorkbook wb = null;
        try {
            File myFile = new File(rutaArchivo + nombreArchivo);
            FileInputStream fis = new FileInputStream(myFile);
            wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            for (int RowNum = 0; RowNum < row_list.size(); RowNum++) {
                XSSFRow row = sheet.createRow(RowNum);
                for (int ColNum = 0; ColNum < row_list.get(0).size(); ColNum++) {
                    XSSFCell cell = row.createCell(ColNum);
                    cell.setCellValue(row_list.get(RowNum).get(ColNum));
                }
            }
            FileOutputStream fileOut = new FileOutputStream(myFile);
            wb.write(fileOut);
            fileOut.close();
            System.out.print("-- Excel de Contraseñas Generadas --");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }



    public ArrayList<ArrayList<String>> readExcelFile() throws Exception {
        FileInputStream file = null;
        XSSFWorkbook wb = null;
        try {
            File myFile = new File(rutaArchivo + nombreArchivo);
            FileInputStream fis = new FileInputStream(myFile);
            wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            ArrayList<String> list = new ArrayList<String>();
            ArrayList<ArrayList<String>> row_list = new ArrayList<ArrayList<String>>();
            int rows = sheet.getLastRowNum();
            System.out.println("rows = " + rows);
            String correo = "";
            Iterator<Row> itr = sheet.iterator();
            for (int i = 0; i <= rows; i++) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (cell.getCellType() == CellType.STRING) { // field that represents string cell type
                            correo = cell.getStringCellValue();
                            System.out.print(cell.getStringCellValue() + "\t\t\t");
                            list.add(cell.getStringCellValue());
                            correo = correo.substring(0, correo.indexOf("@"));
                            list.add(correo);
                            String hashGenerado = BCrypt.hashpw(correo, BCrypt.gensalt(10));
                            list.add(hashGenerado);

//                            Row fila = sheet.createRow(i); //Empieza desde 1...Fila numero 2
//                            fila.createCell(1).setCellValue(correo);
                    }
                    row_list.add(list);
                    list = new ArrayList<String>();
                    System.out.println("");

                }
            }

            return row_list;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
            return new ArrayList<>();
        } finally {
            try {
                if (wb != null) {
                    wb.close();
                }
            } catch (Exception e) {
            }
            try {
                if (file != null) {
                    file.close();
                }
            } catch (Exception e) {
            }
        }


    }
}
