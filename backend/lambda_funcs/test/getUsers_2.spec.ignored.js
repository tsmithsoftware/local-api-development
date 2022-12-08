/**
 * Ignoring due to Mock only stubbing first-line calls to the DAO
 * so the sequelizeInstance is still trying to connect
 * and failing out
 * TODO fix

const UserDAO = require('../daos/userDAO')

jest.clearAllMocks()
jest.mock('../../models/user', () => () => {
  const SequelizeMock = require('sequelize-mock')
  const dbMock = new SequelizeMock()
  const User = dbMock.define('user', {
    userId: 1,
    lastName: 'Wicks',
    firstName: 'John'
  })
  User.$queryInterface.$useHandler(function (query, queryOptions, done) {
    return Promise.reject(new SequelizeMock.Error('DB down'))
  })
  return User
})

describe('Test Sequelize Mocking', () => {
  it('Should handle exception if database is down', async () => {
    await UserDAO.getAllUsers().catch((err) => {
      expect(err.message).toBe('DB down')
      expect(err.name).toBe('SequelizeBaseError')
    })
  })
})

**/
