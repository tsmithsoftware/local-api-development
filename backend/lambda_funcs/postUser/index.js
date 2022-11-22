const { StatusCodes } = require('http-status-codes');
const { response, errorResponse } = require("../../utils/response");
const { getSequelizeClient } = require('../../utils/database_connection');
const { Sequelize, DataTypes } = require('sequelize');
const UserModel = require('../../models/user')

exports.handler = async (event, context) => {
  await getSequelizeClient
    .then(async (client) => {
		const parsedEvent = JSON.parse(event.body)
		let User = UserModel(client, Sequelize)
		await User.create(
		{
			lastName: parsedEvent.lastName,
			firstName: parsedEvent.firstName
		}) 
		.then( user => {
			return response(context, StatusCodes.CREATED, JSON.stringify( {lastName: user.lastName, firstName: user.firstName} ))
		}
		)
		.catch(error => {
		  return errorResponse(context, StatusCodes.INTERNAL_SERVER_ERROR, error)
		})
    })
}
