import 'package:equatable/equatable.dart';

class UserEntity extends Equatable {
  final String uuidString;
  final String lastName;
  final String firstName;

  const UserEntity(
      {required this.lastName, required this.firstName, required this.uuidString});

  @override
  List<Object?> get props => [uuidString, lastName, firstName];
}
