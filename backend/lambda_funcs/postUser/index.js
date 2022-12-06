const { StatusCodes } = require('http-status-codes');
const { response, errorResponse } = require("../../utils/response");
const ConnectionService = require('../../utils/connection_service');
const UserModel = require('../../models/user')

exports.handler = async (event, context) => {
	const connectionService = new ConnectionService();
	await connectionService.getSequelizeClient()
	.then(async (client) => {
		const parsedEvent = JSON.parse(event.body)
		let User = UserModel(client, Sequelize)
		await User.create(
		{
			uuid: parsedEvent.uuid,
			lastName: parsedEvent.lastName,
			firstName: parsedEvent.firstName
		}) 
		.then( user => {
			return response(context, StatusCodes.CREATED, JSON.stringify( {uuid: user.uuid, lastName: user.lastName, firstName: user.firstName} ))
		}
		)
		.catch(error => {
		  return errorResponse(context, StatusCodes.INTERNAL_SERVER_ERROR, error)
		})
    })
}
