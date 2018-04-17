The RESTful service will expose two methods:
Service to return the status of the request to the caller as a String. The two parameters will be added to a JMS queue.
The service is a 'GET' service so that it can be easily test by any broswer.
The url will be http://hostname:port/context/send?i1=value1&i2=value2
Example
http://localhost:8080/restful/send?i1=10&i2=20

----------------------------------------------------------------------------
Service to returns a list of all the elements ever added to the queue from a database in the order added as a JSON structure. 
The service is a 'GET' service so that it can be easily test by any broswer.
The url will be http://hostname:port/context/list
Example
http://localhost:8080/restful/list

To build a war file to deploy
mvn clean package

Database
To keep the implementation as simple as possible, the database to store the data will be an ArrayList.
However, saving and getting data are treated in data repositoy manner.

JMS server
It is assumed that the JMS servier is running on the same machine.