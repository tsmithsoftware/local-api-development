const { StatusCodes } = require('http-status-codes')
const { response, errorResponse } = require('../../utils/response')

exports.handler = async (event, context) => {
  const UserDAO = require('../daos/userDAO')
  await UserDAO.getAllUsers().then((users) => {
    // eslint-disable-next-line object-shorthand
    return response(context, StatusCodes.OK, { users: users })
  }).catch(
    (exception) => {
      return errorResponse(context, StatusCodes.INTERNAL_SERVER_ERROR, exception)
    }
  )
}
