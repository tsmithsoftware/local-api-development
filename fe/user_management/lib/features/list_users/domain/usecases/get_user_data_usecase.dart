import 'package:dartz/dartz.dart';
import 'package:user_management/core/error/failure.dart';
import 'package:user_management/core/usecase.dart';
import 'package:user_management/features/list_users/domain/entities/user_list_entity.dart';
import 'package:user_management/features/list_users/domain/repositories/user_data_repository.dart';

class GetUserDataUseCase extends UseCase<UserListEntity, NoParams>{
  final UserDataRepository repository;

  GetUserDataUseCase({required this.repository});

  @override
  Future<Either<Failure, UserListEntity>> call(NoParams params) async {
    return await repository.getUsers();
  }


}