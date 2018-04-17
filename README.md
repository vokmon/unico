# unico
The application needs to support access by multiple concurrent users. The implementation is based on spring framework.
By default, spring framwork can handle resonable amout of concurrent accesses.
In order to be able to deploy on different web server e.g. JBOSS the application can be built into war package by using the command
mvn package. The output is the war file that can be installed on any web server.


Please see the READEME.txt inside the separated projects for instruction how to run.


