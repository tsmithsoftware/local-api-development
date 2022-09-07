const { User, getSequelizeClient } = require('../../utils/database_connection');
const client = getSequelizeClient();

exports.handler = async (event) => {
	const parsedEvent = JSON.parse(event.body);
	const user = await User.create({lastName: parsedEvent.lastName, firstName: parsedEvent.firstName});
	return JSON.stringify({});
};
