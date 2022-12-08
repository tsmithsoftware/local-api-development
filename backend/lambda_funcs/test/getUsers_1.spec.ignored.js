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

  return User
})

describe('Test Sequelize Mocking', () => {
  it('Should get value from mock', async () => {
    const user = await UserDAO.getAllUsers()
    const firstUser = user[0]
    expect(firstUser.firstName).toEqual('John')
    expect(firstUser.lastName).toEqual('Wicks')
  })
})

**/
