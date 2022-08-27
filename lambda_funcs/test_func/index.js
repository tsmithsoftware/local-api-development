const { getSequelizeClient } = require('../../utils/database_connection');
const client = getSequelizeClient();
//const { response } = require('../../utils/Response');

exports.handler = async (event) => {

    const response = {
        statusCode: 200,
        body: JSON.stringify('Hello from Lambda!'),
    };
    return response;
};
