package com.sita.test.task;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sita.test.task.dto.FileContentDTO;
import com.sita.test.task.processors.SitaFileOrchestrator;

/**
 * Servlet implementation class SitaServlet
 * This class intercepts the browser request and initiates the file processing
 */
public class SitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SitaServlet() {
        super();        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Invokes the orchestrator to read and process the file in the folder
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 	 {
		
		/**
		 * Passes the control to Orchestrator to perform differents tasks
		 */
		FileContentDTO fileContent = null;
		String resultMessage = null;
		String fileName = "";
		try{
			fileContent = new SitaFileOrchestrator().orchestrate();
		}catch(IllegalArgumentException illegalArgumentException){
			resultMessage = "Failure. No files found ";
		}catch(Exception exception){
			resultMessage = "Failure. Exception occured ";
			exception.printStackTrace();
		}
		
		
		
		//Result is constructed to display the status to User		
		if(fileContent != null && fileContent.getErrorData() != null){
			fileName = fileContent.getFileName();
			resultMessage = "Failure. Error record(s) in file : "+fileContent.getErrorData();
		}else if(fileContent != null && fileContent.getProcessedData() != null){
			fileName = fileContent.getFileName();
			resultMessage = "Success. Total Value : "+fileContent.getProcessedData();
		}
		
		//Write to HTML
		PrintWriter out = response.getWriter();		
		out.println(HTML_START + "<h2>Process File:</h2><br/>"
				+ "<h3>File Name : "+fileName+"</h3><br/>"
				+ "<h3>Processing : "+resultMessage+"</h3>"+HTML_END);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}

}