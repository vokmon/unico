The SOAP service will expose the following method as operations:

WSDL of the service
http://hostname:port/context/gdcDetailsWsdl.wsdl

Example
http://localhost:8080/soap/gdcDetailsWsdl.wsdl


GcdRequest returns the greatest common divisor of the two integers at the head of the queue. 
These two elements will subsequently be discarded from the queue and the 
head replaced by the next two in line.
SOAP Envelop example
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sch="http://localhost:8080/xml/gcdc" >
   <soapenv:Header/>
   <soapenv:Body>
      <sch:GcdRequest >
         <sch:num1>15</sch:num1>
         <sch:num2>20</sch:num2>
      </sch:GcdRequest>
   </soapenv:Body>
</soapenv:Envelope>

<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:GcdResponse xmlns:ns2="http://localhost:8080/xml/gcdc">
            <ns2:gcd>5</ns2:gcd>
        </ns2:GcdResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>

GcdListRequest returns a list of all the computed greatest common divisors from a database. 
SOAP Envelop example
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sch="http://localhost:8080/xml/gcdc" >
   <soapenv:Header/>
   <soapenv:Body>
      <sch:GcdListRequest />
   </soapenv:Body>
</soapenv:Envelope>

<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:GcdListResponse xmlns:ns2="http://localhost:8080/xml/gcdc">
            <ns2:gcd>5</ns2:gcd>
            <ns2:gcd>10</ns2:gcd>
            <ns2:gcd>5</ns2:gcd>
        </ns2:GcdListResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>

GcdSumRequest returns the sum of all computed greatest common divisors from a database.
SOAP Envelop example
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sch="http://localhost:8080/xml/gcdc" >
   <soapenv:Header/>
   <soapenv:Body>
      <sch:GcdSumRequest />
   </soapenv:Body>
</soapenv:Envelope>

<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:GcdSumResponse xmlns:ns2="http://localhost:8080/xml/gcdc">
            <ns2:sum>20</ns2:sum>
        </ns2:GcdSumResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>

To build a war file to deploy
mvn clean package

Database
To keep the implementation as simple as possible, the database to store the data will be an ArrayList.
However, saving and getting data are treated in data repositoy manner.


