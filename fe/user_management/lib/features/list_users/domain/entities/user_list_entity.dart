import 'package:equatable/equatable.dart';
import 'package:user_management/features/list_users/domain/entities/user_entity.dart';

class UserListEntity extends Equatable {
  final List<UserEntity> users;

  const UserListEntity(this.users);

  @override
  List<Object?> get props => users;
}