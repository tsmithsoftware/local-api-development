const Sequelize = require('sequelize')
const sequelize = new Sequelize('somedbname', 'myuser', 'mypassword', {
  host: 'mydb.somehost.com',
  dialect: 'mysql'
})

const User = require('../../models/user')(sequelize, Sequelize)

module.exports = {
  getOneUser: function () {
    return new Promise((resolve, reject) => {
      User.findOne().then(user => {
        resolve(user)
      }).catch(err => {
        reject(err)
      })
    })
  }
}
