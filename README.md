FTPINGESTER
===========
Starting point for a spring-boot / camel based FTP file ingestor

Configuration:
--------------

By default, this project starts an ftp server at `localhost:2221` with user "admin" and password "admin".

The admin user's ftp directory is `/tmp/ftpserver`

The ingester will pull files from the `/tmp/ftpserver/test` directory. This directory must be created before running the application:

```
mkdir /tmp/ftpserver/test
```


Running the application
-----------------------
Start the application with 

```
mvn clean install -DskipTests spring-boot:run
```

Testing file consumption
------------------------
While the app is running, create new text files in the `/tmp/ftpserver/test` directory. The contents of these text files will be printed on the command line.
The files will be copied to the `/tmp/ftpserver/test/.done/` directory with the date and time added as the file extension.

