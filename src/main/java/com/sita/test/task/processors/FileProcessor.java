package com.sita.test.task.processors;

import java.io.File;
import java.util.Iterator;

import org.springframework.integration.file.splitter.FileSplitter;
import org.springframework.messaging.Message;

import com.sita.test.task.dto.FileContentDTO;

/**
 * Performs the computation required using values in the file
 * Identifier error data and returns
 */
public class FileProcessor extends FileSplitter{    
	
	/**
	 * Compute and Validate File content
	 * @param message
	 * @return
	 */
    public FileContentDTO process(Message<File> message) {
    	
        StringBuilder errorData = new StringBuilder();
        Integer totalFileValue = new Integer(0);
                          
        Iterator<String> allLinesIterator = (Iterator<String>) splitMessage(message);
    	String eachLine = null;
    	while(allLinesIterator.hasNext()){
    		eachLine = (String)allLinesIterator.next();
    		try{
        		totalFileValue += Integer.parseInt(eachLine);
        	 } catch (NumberFormatException ex) {
        		 errorData.append(eachLine);
             }
    	}
        
    	//Assembler file Content
        FileContentDTO fileContentDTO = new FileContentDTO();        
        if(totalFileValue != 0){
        	fileContentDTO.setProcessedData(totalFileValue.toString());
        }        
        if(errorData != null && errorData.length() > 0){
        	fileContentDTO.setErrorData(errorData.toString());
        }        
                
        return fileContentDTO;
    }
        
}