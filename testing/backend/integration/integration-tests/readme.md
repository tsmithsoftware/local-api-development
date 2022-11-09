ok so integration test framework for an API
REST API calling a database
App class is built and run using Maven
SystemConfig contains the wrapper classes to talk to the API and DB
so first thing the program does is call
SystemConfig.checkConnections to ensure everything is up and running

the integration tests call the API and confirm that: <br/>
a) the database has been correctly updated / the correct data has been pulled <br/>
b) the API response is as expected<br/>
from that call

so we have ApiRequest to model calls to the API <br/>
APIResponse to model the response from the API <br/>
DatabaseChange to model any changes to the DB <br/>
