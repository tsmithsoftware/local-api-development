import sequelize from 'sequelize';

const Sequelize = require('sequelize');

    function getSequelizeClient() {
        return new Promise((resolve, reject) => {
            var initialAttempt =  new Sequelize('db', 'user', 'password', {
                host: 'db',
                dialect: 'mysql'
            });
            initialAttempt
                .authenticate()
                .then(() => {
                    console.log('Connection to database has been established successfully 1.');
                    resolve(sequelize)
                })
                .catch(err => {
                    var secondAttempt = new Sequelize('db', 'user', 'password', {
                        host: 'host.docker.internal',
                        dialect: 'mysql'});
                    secondAttempt
                        .authenticate()
                        .then(() => {
                            console.log('Connection to database has been established 2')
                            resolve(sequelize)
                        })
                        .catch(async err => {
                            var finalAttempt = new Sequelize('db', 'user', 'password', {
                                host: 'localhost',
                                dialect: 'mysql'
                              })
                              await finalAttempt.authenticate().then(() => {
                                console.log('connection to database established 3')
                                resolve(sequelize)
                              })
                              .catch(err => {
                                console.log("connection to DB could not be established")
                                reject(err)
                              })
                        })
                })
                ;
        });
    }

module.exports = getSequelizeClient;