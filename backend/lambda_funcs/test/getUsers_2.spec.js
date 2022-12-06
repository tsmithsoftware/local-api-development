let UserDAO = require('../daos/userDAO');
const SequelizeMock = require("sequelize-mock");


jest.clearAllMocks();
jest.mock('../../models/user', () => ()=> {
    const SequelizeMock = require("sequelize-mock");
    const dbMock = new SequelizeMock();
    var User =  dbMock.define('user', {
        userId: 1,
        lastName: 'John',
        firstName: 'Wicks'
    });

    User.$queryInterface.$useHandler(function(query, queryOptions, done) {
           return Promise.reject(new SequelizeMock.Error("DB down"));
    });

    return User;
});

describe("Test Sequelize Mocking", () => {
      
    it("Should handle exception if database is down", async () => {
        await UserDAO.getOneUser().catch((err) => {
            expect(err.message).toBe("DB down");
            expect(err.name).toBe("SequelizeBaseError");
        });
      })
});