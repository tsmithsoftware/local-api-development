# Sample CF/ Serverless / Integration Testing project

## Tech stack

- NodeJS 14.x+
- Docker
- Postgres (via a Postgres docker image)
- Serverless & Serverless Offline CLI (if local)
- Flutter
- Kotlin
- Selenium
- Power BI
- Kibana
- Elasticsearch
- Debezium

## Overview diagram of project structure
<img src="./project_arch.PNG"/>
The above diagram shows the basic architecture. 

The basic structure is an API Gateway pointing to a set of lambdas, which interact with a Postgres instance. The Postgres instance then feeds into a AWS Redshift data lake. A PBI report would then interact through the Redshift instance.

The basic data model would be visits to stores. 

Integration and UI test frameworks will also be included as part of this project. The integration test framework will follow that currently used in DFE.

The UI test framework will run in a separate container. It will check out the code, build the apk, install the apk on an emulator and run the UI tests against that emulator.

Progress:
30/08 Serverless project and Postgres containers set up and connected. Serverless uses the Sequelize library to communicate with the database.

## Build pipeline
The build pipeline is accessed at the Github Actions section of the repository. This pipeline is intended to:
<ul>
 <li>build and test the FE application code,</li>
 <li>run unit tests against the lambda functions,</li>
 <li>run integration tests against the API and DB backend,</li>
 <li>run UI tests against the FE,</li>
 <li>publish these test reports,</li>
 <li>and generate and upload API documentation. </li>
</ul>

This is a work in progress, as tracked by the associated Actions section of this repo.
