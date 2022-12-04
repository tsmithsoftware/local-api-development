class ServerException implements Exception {
  final String message;

  ServerException(this.message);
}

class CacheException implements Exception {
  final String message;

  CacheException(this.message);
}

class UTimeoutException implements Exception {
  final String message;

  UTimeoutException(this.message);
}