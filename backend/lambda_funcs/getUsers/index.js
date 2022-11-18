const { User } = require('../../utils/database_connection');
const { StatusCodes } = require('http-status-codes');
const { response, errorResponse } = require("../../utils/Response");

exports.handler = async (event, context) => {
  await User.findAll({
  attributes: ['lastName','firstName']
}).then(
  users => {
    return response(context, StatusCodes.OK, users)
  }
)
.catch(error => {
  return errorResponse(context, StatusCodes.INTERNAL_SERVER_ERROR, error)
})
};
