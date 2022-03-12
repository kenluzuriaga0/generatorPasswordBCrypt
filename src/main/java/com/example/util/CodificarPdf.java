package com.example.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

public class CodificarPdf {

    public String convertBinario(String ruta) {
        try {
            //return  new BASE64Encoder().encode(Files.readAllBytes(new File(ruta).toPath())); //otra forma
            return java.util.Base64.getEncoder().encodeToString(Files.readAllBytes(new File(ruta).toPath()));

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String convertStringToBinario(String binario) {
        try {
            System.out.println(binario);
          //  byte[] decodedBytes = new BASE64Decoder().decodeBuffer(binario); //otra forma
           byte[] decodedBytes = java.util.Base64.getDecoder().decode(binario);

            File file = new File("C:\\Users\\kenlu\\Documents\\PROYECTOS\\sam\\build\\web\\resources\\usuarios\\temporal\\2021\\1204573370\\GISIS SA\\dic20\\newfile.pdf");
            FileOutputStream fop = new FileOutputStream(file);

            fop.write(decodedBytes);
            fop.flush();
            fop.close();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
