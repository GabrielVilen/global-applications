/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Class for downloading file.
 *
 * @author Samy
 */
public class FileDownloader {

    public static void startDownload(File file) throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset();
        ec.setResponseContentType(ec.getMimeType(file.getName()));
        ec.setResponseContentLength((int) Math.ceil(file.length()));
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        OutputStream out = ec.getResponseOutputStream();
        InputStream in = new FileInputStream(file);
        byte[] bytesBuffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = in.read(bytesBuffer)) > 0) {
            out.write(bytesBuffer, 0, bytesRead);
        }

        out.flush();
        in.close();
        out.close();
        fc.responseComplete();
    }

}
