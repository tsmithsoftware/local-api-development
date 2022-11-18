const Sequelize = require('sequelize');

var sequelize = new Sequelize('db', 'user', 'password', {
    host: 'db',
    dialect: 'mysql'
});

var getSequelizeClient = new Promise((resolve, reject) => {
  console.log("sequelize is:" + sequelize)
sequelize.authenticate().then(() => {
    console.log('Connection to database has been established successfully 1.');
    resolve(sequelize)
}).catch(err => {

    sequelize = new Sequelize('db', 'user', 'password', {
      host: 'host.docker.internal',
      dialect: 'mysql'});

      sequelize.authenticate().then(() => {
        console.log('Connection to database has been established 2')
        resolve(sequelize)
      })
      .catch(async err => {

        sequelize = new Sequelize('db', 'user', 'password', {
          host: 'localhost',
          dialect: 'mysql'
        })
        
        await sequelize.authenticate().then(() => {
          console.log('connection to database established 3')
          resolve(sequelize)
        })
        .catch(err => {
          console.log("connection to DB could not be established")
          reject(err)
        })

      })
  });
})

module.exports = {
  sequelize,
  getSequelizeClient
}