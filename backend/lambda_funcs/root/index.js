const { User, getSequelizeClient } = require('../../utils/database_connection');
const client = getSequelizeClient();

exports.handler = async (event) => {
	return { statusCode: 204 }
};
