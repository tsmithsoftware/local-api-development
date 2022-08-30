FROM ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive

RUN export SLS_DEBUG=*

# Node 18x, https://github.com/nodesource/distributions
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install openjdk-11-jdk -y && \
    apt install curl -y && \
    curl -sL https://deb.nodesource.com/setup_current.x | bash - && \
    apt install nodejs -y

ADD ./ /app
WORKDIR /app

RUN npm install

# Serverless port
EXPOSE 3000
# NodeJS debug port
EXPOSE 9229

CMD npm run docker
