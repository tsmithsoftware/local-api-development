const { User, getSequelizeClient } = require('../../utils/database_connection');
const client = getSequelizeClient();

exports.handler = async (event) => {
    const users = await User.findAll({
		attributes: ['lastName','firstName']
	});
    return {"users": users};
};
