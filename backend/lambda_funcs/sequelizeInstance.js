const Sequelize = require('sequelize')
let sequelizeInstance = new Sequelize('db', 'user', 'password', {
  host: 'db',
  dialect: 'mysql',
  pool: {
    max: 15,
    min: 5,
    idle: 20000,
    evict: 15000,
    acquire: 30000
  }
})

sequelizeInstance.authenticate().then(() => {
  console.log('Connection to database has been established successfully.')
}).catch(_ => {
  sequelizeInstance = new Sequelize('db', 'user', 'password', {
    host: 'host.docker.internal',
    dialect: 'mysql',
    pool: {
      max: 15,
      min: 5,
      idle: 20000,
      evict: 15000,
      acquire: 30000
    }
  })
}).catch(_ => {
  sequelizeInstance = new Sequelize('db', 'user', 'password', {
    host: 'localhost',
    dialect: 'mysql',
    pool: {
      max: 15,
      min: 5,
      idle: 20000,
      evict: 15000,
      acquire: 30000
    }
  })
}).catch(_ => {
  console.error('Connection cannot be established.')
})

const db = {}

db.sequelizeInstance = sequelizeInstance
db.Sequelize = Sequelize

module.exports = db
