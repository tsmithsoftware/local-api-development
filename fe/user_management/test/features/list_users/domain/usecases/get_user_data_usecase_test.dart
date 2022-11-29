import 'package:dartz/dartz.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:user_management/core/usecase.dart';
import 'package:user_management/features/list_users/domain/entities/user_entity.dart';
import 'package:user_management/features/list_users/domain/entities/user_list_entity.dart';
import 'package:user_management/features/list_users/domain/repositories/user_data_repository.dart';
import 'package:user_management/features/list_users/domain/usecases/get_user_data_usecase.dart';

@GenerateNiceMocks([MockSpec<UserDataRepository>()])
import 'get_user_data_usecase_test.mocks.dart';

  void main() {
    late MockUserDataRepository mockRepo;
    late GetUserDataUseCase getUserDataUseCase;

    setUp(() {
      mockRepo = MockUserDataRepository();
      getUserDataUseCase = GetUserDataUseCase(repository: mockRepo);
    });

    const user = UserEntity(lastName: "lastName", firstName: "firstName");
    const userList = UserListEntity([user]);

    test('should get users from the repository', () async {
      when(mockRepo.getUsers()).thenAnswer((_) async => const Right(userList));
      final result = await getUserDataUseCase(NoParams());
      expect(result, const Right(userList));
      verify(mockRepo.getUsers());
      verifyNoMoreInteractions(mockRepo);
    });
  }
