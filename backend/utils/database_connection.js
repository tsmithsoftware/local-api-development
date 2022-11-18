const { Sequelize } = require('sequelize');
const UserModel = require('../models/user')

  let sequelize = new Sequelize('db', 'user', 'password', {
    host: 'db',
    dialect: 'mysql'
  });
  
  let User = UserModel(sequelize, Sequelize)

  async function getSequelizeClient() {
    try {
      await sequelize.authenticate();
      console.log('Connection has been established successfully.');
    } catch (error) {
      console.error('Unable to connect to the database:', error);
      console.log("attempting to connect via docker internal host connection");
      try {
            sequelize = new Sequelize('db', 'user', 'password', {
              host: 'host.docker.internal',
              dialect: 'mysql'
              });
          } catch (error) {
            console.error('Unable to connect to the database:', error);
            console.log("attempting to connect via localhost connection");
            sequelize = new Sequelize
              (
                'db',
                'user',
                'password',
                {
                  host: 'localhost',
                  dialect: 'mysql'
                }
              )
          }
        }
  }

module.exports= {
 getSequelizeClient,
 User
 }
