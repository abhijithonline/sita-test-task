# sita-test-task
Contains the files to process a file and compute total from the file content

Please use URL(with tomcat port) http://localhost:8080/sita-test-task/process

The web application is created using a simple servlet which invokes Spring Integration

Technologies Used

Java 7
Spring Integration 4.3.0.RELEASE
Maven 3
Junit

Build C:\SITA_TEST_TASK using mvn install to create war
Place the war file into the webapp folder of Tomcat (or any other web server)
Start the server and go to http://localhost:8080/sita-test-task/process
Requests from browser are processed by SitaServlet which invoke other classes
The page will display the file processed and result
As per instructions, processed and failed files are moved to respective folders

Any queries, please contact abhijithonline@gmail.com
