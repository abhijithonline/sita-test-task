package com.sita.test.task.processors;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import com.sita.test.task.dto.FileContentDTO;

/**
 * Performs the reading operating using Spring Integration APIs
 */
public class SitaFileReadProcessor {	
	private final String INBOUND_PATH = "C:/SITA_TEST_TASK/IN/";
	private static final String HEADER_FILE_NAME = "file_name";	
	
	/**
	 * Read and transform the file contents
	 * @return
	 */
	public FileContentDTO read() {		
		
		//Obtain the message file
		Message<File> messageFile = (Message<File>)fileReadingMessageSource().receive();
		
		//Transforms the message file to String		
		Message<String> messageString = (Message<String>)fileToStringTransformer().transform(messageFile);		
		
		//Process the message file data and split to multiple lines
		//Assembles the file content result
		FileContentDTO fileContentDTO = fileProcessor().process( messageFile );
		fileContentDTO.setMessage(messageString);
		fileContentDTO.setFileName((String) messageString.getHeaders().get(HEADER_FILE_NAME));
		
		return fileContentDTO;				
	}	
	
	
   @Bean
    public MessageChannel fileInputChannel() {
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(value = "fileInputChannel", poller = @Poller(fixedDelay = "1"))
    public MessageSource<File> fileReadingMessageSource() {
    	CompositeFileListFilter<File> filters =new CompositeFileListFilter<>();
		filters.addFilter(new SimplePatternFileListFilter("*.txt"));		

		FileReadingMessageSource source = new FileReadingMessageSource();
		source.setAutoCreateDirectory(true);
		source.setDirectory(new File(INBOUND_PATH));
		source.setFilter(filters);

		return source;
    }

    @Bean
	public FileToStringTransformer fileToStringTransformer() {
		return new FileToStringTransformer();
	}
    
    @Bean
	public IntegrationFlow processFileFlow() {
		return IntegrationFlows
				.from("fileInputChannel")
				.transform(fileToStringTransformer())
				.handle("fileProcessor", "process").get();
	}
    
    @Bean
	public FileProcessor fileProcessor() {
		return new FileProcessor();
	}

}	

