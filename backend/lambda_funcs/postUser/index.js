const { StatusCodes } = require('http-status-codes')
const { response, errorResponse } = require('../../utils/response')

exports.handler = async (event, context) => {
  const UserDAO = require('../daos/userDAO')
  await UserDAO.postUser(event)
    .then((user) => {
      // eslint-disable-next-line object-shorthand
      return response(context, StatusCodes.CREATED, JSON.stringify({ uuid: user.uuid, lastName: user.lastName, firstName: user.firstName }))
    }).catch(error => {
      return errorResponse(context, StatusCodes.INTERNAL_SERVER_ERROR, error)
    })
}
