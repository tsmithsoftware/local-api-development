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
}).catch(err => {
  console.error('Unable to connect to database:', err)
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
})

const db = {}

db.sequelizeInstance = sequelizeInstance
db.Sequelize = Sequelize

module.exports = db
