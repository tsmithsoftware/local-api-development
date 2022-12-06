import { handler } from '../getUsers';
import lambdaTester from 'lambda-tester';
let UserDAO = require('../daos/userDAO');

jest.clearAllMocks();
jest.mock('../../models/user', () => ()=> {
    const SequelizeMock = require("sequelize-mock");
    const dbMock = new SequelizeMock();
    var User =  dbMock.define('user', {
        userId: 1,
        lastName: 'Wicks',
        firstName: 'John'
    });

    return User;
});
describe("Test Sequelize Mocking", () => {

    it("Should get value from mock", async () => {
        const user = await UserDAO.getOneUser();
        expect(user.firstName).toEqual('John');
        expect(user.lastName).toEqual('Wicks');
      })
    
});
