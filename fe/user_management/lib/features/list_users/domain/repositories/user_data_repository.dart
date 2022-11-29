import 'package:dartz/dartz.dart';
import 'package:user_management/core/error/failure.dart';
import 'package:user_management/features/list_users/domain/entities/user_list_entity.dart';

abstract class UserDataRepository {
  Future<Either<Failure, UserListEntity>> getUsers();
}