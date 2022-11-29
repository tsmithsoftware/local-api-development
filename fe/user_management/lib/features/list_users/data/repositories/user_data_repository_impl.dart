import 'package:dartz/dartz.dart';
import 'package:user_management/core/error/failure.dart';
import 'package:user_management/core/platform/network_info.dart';
import 'package:user_management/features/list_users/data/datasources/user_local_data_source.dart';
import 'package:user_management/features/list_users/domain/entities/user_list_entity.dart';
import 'package:user_management/features/list_users/domain/repositories/user_data_repository.dart';
import 'package:user_management/features/list_users/data/datasources/user_remote_data_source.dart';

class UserDataRepositoryImpl extends UserDataRepository {
  final UserLocalDataSource localDataSource;
  final UserRemoteDataSource remoteDataSource;
  final NetworkInfo networkInfo;

  UserDataRepositoryImpl({
    required this.remoteDataSource,
    required this.localDataSource,
    required this.networkInfo,
  });

  @override
  Future<Either<Failure, UserListEntity>> getUsers() async {
    if(await networkInfo.isConnected) {
      try {
        final result = await remoteDataSource.getUsers();
        localDataSource.cacheUsers(result);
        return(Right(result));
      } catch (e) {
        return Left(ServerFailure());
      }
    } else {
      final localUsers = await localDataSource.getUsers();
      return Right(localUsers);
    }
  }
}