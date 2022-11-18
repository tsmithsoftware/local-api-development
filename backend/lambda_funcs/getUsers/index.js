const { StatusCodes } = require('http-status-codes');
const { response, errorResponse } = require("../../utils/response");
const { getSequelizeClient } = require('../../utils/database_connection');
const { Sequelize, DataTypes } = require('sequelize');
const UserModel = require('../../models/user')

exports.handler = async (event, context) => {
  await getSequelizeClient
    .then(async (client) => {
      let User = UserModel(client, Sequelize)
      await User.findAll({
        attributes: ['lastName','firstName']
      }).then(
        users => {
          return response(context, StatusCodes.OK, users)
        }
      )
    })
    .catch(error => {
      return errorResponse(context, StatusCodes.INTERNAL_SERVER_ERROR, error)
    })
};
