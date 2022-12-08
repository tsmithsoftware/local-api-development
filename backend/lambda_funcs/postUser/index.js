const { StatusCodes } = require('http-status-codes')
const { response, errorResponse } = require('../../utils/response')
const ConnectionService = require('../../utils/connection_service')
const UserModel = require('../../models/user')
const Sequelize = require('sequelize')

exports.handler = async (event, context) => {
  const connectionService = new ConnectionService()
  await connectionService.getSequelizeClient()
    .then(async (client) => {
      const parsedEvent = JSON.parse(event.body)
      const User = UserModel(client, Sequelize)
      await User.create(
        {
          uuid: parsedEvent.uuid,
          lastName: parsedEvent.lastName,
          firstName: parsedEvent.firstName
        })
        .then(user => {
          return response(context, StatusCodes.CREATED, JSON.stringify({ uuid: user.uuid, lastName: user.lastName, firstName: user.firstName }))
        }
        )
        .catch(error => {
          return errorResponse(context, StatusCodes.INTERNAL_SERVER_ERROR, error)
        })
    })
}
