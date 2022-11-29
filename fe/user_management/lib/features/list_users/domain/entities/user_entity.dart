import 'package:equatable/equatable.dart';

class UserEntity extends Equatable {
  final String lastName;
  final String firstName;

  const UserEntity({ required this.lastName, required this.firstName} );

  @override
  List<Object?> get props => [lastName, firstName];

}