const db = require('../sequelizeInstance')
const sequelizeInstance = db.sequelizeInstance
const Sequelize = db.Sequelize
const User = require('../../models/user')(sequelizeInstance, Sequelize)

module.exports = {
  getAllUsers: function () {
    return new Promise((resolve, reject) => {
      User.findAll(
        {
          attributes: ['lastName', 'firstName', 'uuid']
        }
      ).then(users => {
        resolve(users)
      }).catch(err => {
        reject(err)
      })
    })
  },
  postUser: function (event) {
    return new Promise((resolve, reject) => {
      const parsedEvent = JSON.parse(event.body)
      User.create(
        {
          uuid: parsedEvent.uuid,
          lastName: parsedEvent.lastName,
          firstName: parsedEvent.firstName
        })
        .then(user => {
          resolve(user)
        }
        )
        .catch(error => {
          reject(error)
        })
    })
  }
}
