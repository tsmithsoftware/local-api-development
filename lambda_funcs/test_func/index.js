const { getSequelizeClient } = require ('../../utils/database_connection');

exports.handler = async () => {

  let message = 'Session is alive';

  return {
    "statusCode": 200,
    "message": message
  };

};
