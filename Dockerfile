FROM ubuntu:18.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && \ 
    apt-get upgrade -y && \
    apt-get install openjdk-11-jdk -y && \
    apt install curl -y && \
    curl -sL https://deb.nodesource.com/setup_14.x | bash - && \
    apt install nodejs -y && \
    apt install maven -y

ADD ./ /app
WORKDIR /app

RUN npm version
RUN npm install

# Serverless port
EXPOSE 3000
# NodeJS debug port
EXPOSE 9229

CMD npm run docker
