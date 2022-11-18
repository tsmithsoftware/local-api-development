const { User } = require('../../utils/database_connection');

exports.handler = async (event, context) => {
	const parsedEvent = JSON.parse(event.body);
	await User.create({lastName: parsedEvent.lastName, firstName: parsedEvent.firstName})
		.then(
			user => {
				return response(context, StatusCodes.CREATED,  JSON.stringify({ lastName: user.lastName, firstName: user.firstName }))
			}
		)
		.catch(error => {
			return errorResponse(context, StatusCodes.INTERNAL_SERVER_ERROR, error)
		}
		)
};
