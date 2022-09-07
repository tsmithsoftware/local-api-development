const { User, getSequelizeClient } = require('../../utils/database_connection');
const client = getSequelizeClient();

exports.handler = async (event) => {
	const parsedUser = JSON.parse(event);
	const user = await User.create({lastName: event.lastName, firstName: event.firstName});
	return {};
};
