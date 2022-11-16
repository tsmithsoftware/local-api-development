FROM node:18.8

WORKDIR /app

COPY serverless.yml ./
COPY serverless.doc.yml ./
COPY package.json ./
COPY package-lock.json ./

RUN npm install
RUN npm ci

COPY . .

EXPOSE 3000
EXPOSE 3002

CMD ["node", "./node_modules/serverless/bin/serverless.js", "offline", "start",  "--host", "0.0.0.0"]