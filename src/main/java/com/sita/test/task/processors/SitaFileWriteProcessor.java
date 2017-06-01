package com.sita.test.task.processors;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.springframework.integration.file.FileHeaders;

import com.sita.test.task.dto.FileContentDTO;


public class SitaFileWriteProcessor {
	
	private final String OUTBOUND_PATH = "C:/SITA_TEST_TASK/OUT/";
	private final String ERROR_PATH = "C:/SITA_TEST_TASK/ERROR/";
	private final String PROCESSED = ".PROCESSED";
	private final String ERROR = ".ERROR";
	
	public void write(FileContentDTO fileContentDTO) {
				
		try {
			
			if(fileContentDTO.getErrorData() != null && fileContentDTO.getErrorData().length() > 0){
				
				File newFile = new File(ERROR_PATH + fileContentDTO.getFileName() + ERROR);
				writeFile(fileContentDTO.getErrorData(), newFile);
				
			}else{
				
				File newFile = new File(OUTBOUND_PATH + fileContentDTO.getFileName() + PROCESSED);
				writeFile(fileContentDTO.getProcessedData(), newFile);
								
			}
			
			moveFile(fileContentDTO);
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param fileName
	 * @param content
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void writeFile(String content, File file) throws IOException, InterruptedException {
		
		FileUtils.writeStringToFile(file, content, Charset.forName("UTF-8"), false);		
	}
	
	private void moveFile(FileContentDTO fileContentDTO){
				
		File file = fileContentDTO.getMessage().getHeaders().get(FileHeaders.ORIGINAL_FILE, File.class);
		
		if(fileContentDTO.getErrorData() != null){
			file.renameTo(new File(ERROR_PATH, file.getName()));
		}else{
			file.renameTo(new File(OUTBOUND_PATH, file.getName()));
		}
		
		
	}
	
	
	
}	
