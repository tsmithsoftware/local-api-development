const { StatusCodes } = require('http-status-codes')
const { response, errorResponse } = require('../../utils/response')

exports.handler = async (event, context) => {
  const UserDAO = require('../daos/UserDAO')
  await UserDAO.getOneUser().then((user) => {
    return response(context, StatusCodes.OK, JSON.stringify(user))
  }).catch(
    (exception) => {
      return errorResponse(context, StatusCodes.INTERNAL_SERVER_ERROR, exception)
    }
  )
}
