import 'package:bloc_test/bloc_test.dart';
import 'package:dartz/dartz.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:user_management/core/constants.dart';
import 'package:user_management/core/error/failure.dart';
import 'package:user_management/features/list_users/domain/entities/user_list_entity.dart';
import 'package:user_management/features/list_users/domain/usecases/get_user_data_usecase.dart';
import 'package:user_management/features/list_users/presentation/bloc/get_user_bloc.dart';
import 'package:user_management/features/list_users/presentation/bloc/get_user_event.dart';
import 'package:user_management/features/list_users/presentation/bloc/get_user_states.dart';

import 'get_user_bloc_test.mocks.dart';

@GenerateMocks([GetUserDataUseCase, UserListEntity])
void main() {
  late GetUserBloc bloc;
  late MockGetUserDataUseCase mockGetUserDataUseCase;
  late MockUserListEntity mockUserListEntity;

  setUp(() {
    mockUserListEntity = MockUserListEntity();
    mockGetUserDataUseCase = MockGetUserDataUseCase();
    bloc = GetUserBloc(useCase: mockGetUserDataUseCase);
  });

  group('GetUserBloc', () {
    blocTest(
      'emits [] when nothing is added',
      build: () {
        when(mockGetUserDataUseCase.call(any))
            .thenAnswer((_) async => Right(mockUserListEntity));
        return bloc;
      },
      expect: () => [],
    );

    blocTest(
      'emits GetUsersEmpty, GetUsersLoading, GetUsersLoaded() when GetUsersEvent is added',
      build: () {
        when(mockGetUserDataUseCase.call(any))
            .thenAnswer((_) async => Right(mockUserListEntity));
        return bloc;
      },
      act: (bloc) => bloc.add(const GetUsersEvent()),
      expect: () => [
        GetUsersEmpty(),
        GetUsersLoading(),
        GetUsersLoaded(users: mockUserListEntity)
      ],
    );

    blocTest(
        'should emit GetUsersEmpty, GetUsersLoading, GetUsersError when loading fails',
        build: () {
          final serverFailure = const ServerFailure(serverFailureMessage);
          when(mockGetUserDataUseCase.call(any))
              .thenAnswer((_) async => Left(serverFailure));
          return bloc;
        },
        act: (bloc) => bloc.add(const GetUsersEvent()),
        expect: () => [
              GetUsersEmpty(),
              GetUsersLoading(),
              GetUsersError(message: serverFailureMessage)
            ]);

    blocTest('should get data from concrete use case',
        build: () {
          when(mockGetUserDataUseCase.call(any))
              .thenAnswer((_) async => Right(mockUserListEntity));
          return bloc;
        },
        act: (bloc) => bloc.add(const GetUsersEvent()),
        expect: () => [
              GetUsersEmpty(),
              GetUsersLoading(),
              GetUsersLoaded(users: mockUserListEntity)
            ],
        verify: (bloc) async {
          verify(mockGetUserDataUseCase(any));
        });

    blocTest(
        'should emit GetUsersEmpty, GetUsersLoading, GetUsersError'
        'with correct message when loading fails with CacheFailure',
        build: () {
          CacheFailure failure = const CacheFailure(cacheFailureMessage);
          when(mockGetUserDataUseCase.call(any))
              .thenAnswer((_) async => Left(failure));
          return bloc;
        },
        act: (bloc) => bloc.add(const GetUsersEvent()),
        expect: () => [
              GetUsersEmpty(),
              GetUsersLoading(),
              GetUsersError(message: cacheFailureMessage)
            ]);
  });
}
