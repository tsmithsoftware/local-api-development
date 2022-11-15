const { sequelize } = require('../utils/database_connection')

module.exports = (sequelize, type) => {
    return sequelize.define('user', {
        userId: {
          type: type.INTEGER,
          primaryKey: true,
          autoIncrement: true
        },
        lastName: type.STRING,
	firstName: type.STRING
    })
}
