import 'package:equatable/equatable.dart';

abstract class Failure extends Equatable {
  final String message;
  const Failure(this.message) : super();
}

// General failures
class ServerFailure extends Failure {
  // ignore: unused_field
  final String _message;
  const ServerFailure(this._message) : super(_message);

  @override
  List<Object?> get props => [];
}

class UTimeoutFailure extends Failure {
  // ignore: unused_field
  final String _message;
  const UTimeoutFailure(this._message): super(_message);

  @override
  List<Object?> get props => [];
}

class CacheFailure extends Failure {
  // ignore: unused_field
  final String _message;
  const CacheFailure(this._message):super(_message);

  @override
  List<Object?> get props => [];
}
