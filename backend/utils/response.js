const defaultHeaders = { 'Content-Type': 'application/json' };

function response(context, statusCode, body) {

  let responseBody = body;
  if (typeof body === 'string') responseBody = JSON.parse(body);

  context.succeed({
    statusCode: statusCode,
    headers: defaultHeaders,
    isBase64Encoded: false,
    body: JSON.stringify(responseBody),
  });
}

function errorResponse(context, statusCode, err) {

  let errorToRespond = err;
  if (typeof errorToRespond === 'string') {
    errorToRespond = new Error(errorToRespond);
  }

  context.succeed({
    statusCode: statusCode,
    headers: defaultHeaders,
    isBase64Encoded: false,
    body: JSON.stringify({ errorMessage: errorToRespond.message }),
  });
}

module.exports = { response, errorResponse };
