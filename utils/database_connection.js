const { Sequelize } = require('sequelize');
const UserModel = require('../models/user')

// Option 3: Passing parameters separately (other dialects)
// add "if" for local dev

  const sequelize = new Sequelize('db', 'user', 'password', {
    host: 'localhost',
    dialect: 'mysql'
  });
  
  const User = UserModel(sequelize, Sequelize)

async function getSequelizeClient() {

  try {
    await sequelize.authenticate();
    console.log('Connection has been established successfully.');
  } catch (error) {
    console.error('Unable to connect to the database:', error);
  }

  return sequelize;

}

module.exports= {
 getSequelizeClient,
 User
 };
