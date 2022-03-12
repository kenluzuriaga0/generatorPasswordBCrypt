package com.example.nek;

import com.example.util.CodificarPdf;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

      /*  ReadExcel rexcel = new ReadExcel("C:\\Users\\kenlu\\Documents\\PROYECTOS\\", "usuario.xlsx");
        try {
            ArrayList<ArrayList<String>> row_list  = rexcel.readExcelFile();
           // System.out.println(row_list);
            rexcel.writeExcelFile(row_list);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        CodificarPdf cod = new CodificarPdf();
        cod.convertStringToBinario(cod.convertBinario("C:\\Users\\kenlu\\Documents\\PROYECTOS\\sam\\build\\web\\resources\\usuarios\\temporal\\2021\\1204573370\\GISIS SA\\dic20\\167702-2389-1204573370-20122021-ORDMEDICA.pdf"));
    }
}
