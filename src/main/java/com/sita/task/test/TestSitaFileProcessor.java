package com.sita.task.test;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.sita.test.task.dto.FileContentDTO;
import com.sita.test.task.processors.SitaFileOrchestrator;

public class TestSitaFileProcessor {

	 @Test
	 public void testSitaFileProcessor() {
		
			
		FileContentDTO fileContent = new SitaFileOrchestrator().orchestrate();
				
		assertSame("133", fileContent.getProcessedData());
		
	 }
	 
}
