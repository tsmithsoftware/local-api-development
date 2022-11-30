import 'package:dartz/dartz.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:user_management/core/error/exception.dart';
import 'package:user_management/core/error/failure.dart';
import 'package:user_management/core/platform/network_info.dart';
import 'package:user_management/features/list_users/data/datasources/user_local_data_source.dart';
import 'package:user_management/features/list_users/data/datasources/user_remote_data_source.dart';
import 'package:user_management/features/list_users/data/models/user_list_model.dart';
import 'package:user_management/features/list_users/data/models/user_model.dart';
import 'package:user_management/features/list_users/data/repositories/user_data_repository_impl.dart';

import 'user_data_repository_impl_test.mocks.dart';

@GenerateMocks([UserRemoteDataSource, UserLocalDataSource, NetworkInfo])
void main() {

  late UserDataRepositoryImpl repository;
  late MockUserRemoteDataSource mockRemoteDataSource;
  late MockUserLocalDataSource mockLocalDataSource;
  late MockNetworkInfo mockNetworkInfo;

  setUp(() {
    mockRemoteDataSource = MockUserRemoteDataSource();
    mockLocalDataSource = MockUserLocalDataSource();
    mockNetworkInfo = MockNetworkInfo();
    repository = UserDataRepositoryImpl(
      remoteDataSource: mockRemoteDataSource,
      localDataSource: mockLocalDataSource,
      networkInfo: mockNetworkInfo,
    );
  });

  void runTestsOnline(Function body) {
    group('device is online', () {
      setUp(() {
        when(mockNetworkInfo.isConnected).thenAnswer((_) async => true);
      });

      body();
    });
  }

  void runTestsOffline(Function body) {
    group('device is offline', () {
      setUp(() {
        when(mockNetworkInfo.isConnected).thenAnswer((_) async => false);
      });

      body();
    });
  }

  group('getUsers', (){
    const user = UserModel(lastName: "Tom", firstName: "Tom");
    const userList = UserListModel(users: [user]);

    test('should check if device is online', () async {
      when(mockNetworkInfo.isConnected).thenAnswer((_) async => true);
      when(mockRemoteDataSource.getUsers())
          .thenAnswer((_) async => userList);
      await repository.getUsers();
      verify(mockNetworkInfo.isConnected);
    });

    runTestsOnline(() {

      test(
        'should return remote data when the call to remote data source is successful',
            () async {
          // arrange
          when(mockRemoteDataSource.getUsers())
              .thenAnswer((_) async => userList);
          // act
          final result = await repository.getUsers();
          // assert
          verify(mockRemoteDataSource.getUsers());
          expect(result, equals(const Right(userList)));
        },
      );

      test(
        'should cache the data locally when the call to remote data source is successful',
            () async {
          // arrange
              when(mockRemoteDataSource.getUsers())
                  .thenAnswer((_) async => userList);
          // act
          await repository.getUsers();
          // assert
          verify(mockRemoteDataSource.getUsers());
          verify(mockLocalDataSource.cacheUsers(userList));
        },
      );

      test(
        'should return server failure when the call to remote data source is unsuccessful',
            () async {
          // arrange
          when(mockRemoteDataSource.getUsers())
              .thenThrow(ServerException());
          // act
          final result = await repository.getUsers();
          // assert
          verify(mockRemoteDataSource.getUsers());
          verifyZeroInteractions(mockLocalDataSource);
          expect(result, equals(Left(ServerFailure())));
        },
      );
    });

    runTestsOffline(()
    {

      test(
        'should return last locally cached data when the cached data is present',
            () async {
          // arrange
          when(mockLocalDataSource.getUsers())
              .thenAnswer((_) async => userList);
          // act
          final result = await repository.getUsers();
          // assert
          verifyZeroInteractions(mockRemoteDataSource);
          verify(mockLocalDataSource.getUsers());
          expect(result, equals(const Right(userList)));
        },
      );

      test(
        'should return CacheFailure when there is no cached data present',
            () async {
          // arrange
          when(mockLocalDataSource.getUsers())
              .thenThrow(CacheException());
          // act
          final result = await repository.getUsers();
          // assert
          verifyZeroInteractions(mockRemoteDataSource);
          verify(mockLocalDataSource.getUsers());
          expect(result, equals(Left(CacheFailure())));
        },
      );
    });

  });
}