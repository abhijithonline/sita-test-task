package com.sita.test.task.processors;

import com.sita.test.task.dto.FileContentDTO;

/**
 * Orchestrate the reading and writing of Files
 * Results are consolidated
 * @return
 */
public class SitaFileOrchestrator {
		
	/**
	 * Orchestrate the processing of the file
	 * @return
	 */
	public FileContentDTO orchestrate(){
		
		//Reading the file and validating the data
		SitaFileReadProcessor readProcessor = new SitaFileReadProcessor();
		FileContentDTO result = readProcessor.read();				
		
		//Flush the content to respective files and directories
		new SitaFileWriteProcessor().write(result);
		
		return result;
	}
}
