FROM node:18.8

WORKDIR /app

COPY . .

RUN npm install
RUN npm ci


EXPOSE 3000
EXPOSE 3002

CMD ["node", "./node_modules/serverless/bin/serverless.js", "offline", "start",  "--host", "0.0.0.0"]
