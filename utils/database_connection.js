const { Sequelize } = require('sequelize');

// Option 3: Passing parameters separately (other dialects)
// add "if" for local dev

async function getSequelizeClient() {
  const sequelize = new Sequelize('db', 'user', 'password', {
    host: 'localhost',
    dialect: mysql
  });

  try {
    await sequelize.authenticate();
    console.log('Connection has been established successfully.');
  } catch (error) {
    console.error('Unable to connect to the database:', error);
  }

  return sequelize;

}
