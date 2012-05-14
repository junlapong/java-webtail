package org.webtail.tail;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadZipFile
 */
public class DownloadZipFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String strLogFile;
	private String strLogDir;
	private static final int iBUFFER = 2048;
    /**
     * Default constructor. 
     */
    public DownloadZipFile() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		strLogDir = this.getServletConfig().getServletContext().getInitParameter("logdir");
		try {
			strLogFile = request.getParameter("logfile");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		File f = new File(strLogDir + "/" + strLogFile);
		if (!f.exists()) {
			return;
		}
		
		response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + strLogFile + ".zip");
		
		ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
		
		FileInputStream fis = new FileInputStream(f);
		
		ZipEntry entry = new ZipEntry(f.getName());
		zos.putNextEntry(entry);
		
		byte bData[] = new byte[iBUFFER];
		int i = 0;
		while ((i = fis.read(bData)) != -1) {
			zos.write(bData, 0, i);
		}
		zos.closeEntry();
		fis.close();
		zos.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
