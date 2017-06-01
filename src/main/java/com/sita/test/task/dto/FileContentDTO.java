package com.sita.test.task.dto;

import org.springframework.messaging.Message;

public class FileContentDTO {

	private String errorData;
	
	private String processedData;
	
	private String fileName;
	
	private Message<String> message;

	/**
	 * @return the errorData
	 */
	public String getErrorData() {
		return errorData;
	}

	/**
	 * @param errorData the errorData to set
	 */
	public void setErrorData(String errorData) {
		this.errorData = errorData;
	}

	/**
	 * @return the processedData
	 */
	public String getProcessedData() {
		return processedData;
	}

	/**
	 * @param processedData the processedData to set
	 */
	public void setProcessedData(String processedData) {
		this.processedData = processedData;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileContentDTO [errorData=" + errorData + ", processedData=" + processedData + ", fileName=" + fileName
				+ "]";
	}

	/**
	 * @return the message
	 */
	public Message<String> getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(Message<String> message) {
		this.message = message;
	}
	
	
}
