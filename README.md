# Local API / Automation Testing project

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

The basic structure is an API Gateway pointing to a set of lambdas, which interact with a Postgres instance. The Postgres instance then feeds into a another database representing a data lake. A PBI report would then interact through the "lake" instance.

The basic data model would be keeping track of users of an application.

Integration and UI test frameworks will also be included as part of this project.

The UI test framework will run in a separate container. It will check out the FE code, build the apk, install the apk on an emulator and run the UI tests against that emulator.

Progress is tracked via the associated Actions section of this repository.

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

## Generating /uploading API documentation
API documentation is generated using a combination of <a href="https://www.npmjs.com/package/serverless-openapi">serverless-openapi</a> and <a href="https://redocly.com/docs/redoc/deployment/cli/">redoc-cli</a> to generate HTML documentation. The generated documentation is then uploaded to <a href="https://pages.github.com/">Github Pages</a> using a <a href="https://github.com/marketplace/actions/github-pages-action">Github Pages Action.</a>

The documentation is generated by running:

 ``serverless openapi generate --output documentation/openapi/openapi.yaml --format yaml``

 ``redoc-cli build -o index.html openapi.yaml``

 This generates a HTML page with an overview of the API endpoints available in the project.
 
 The API documentation can be accessed at https://tsmithsoftware.github.io/local-api-development/
