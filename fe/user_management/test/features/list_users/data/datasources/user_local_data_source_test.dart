import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:objectbox/objectbox.dart';
import 'package:user_management/core/error/exception.dart';
import 'package:user_management/features/list_users/data/datasources/user_local_data_source.dart';
import 'package:user_management/features/list_users/data/models/user_list_model.dart';
import 'package:user_management/features/list_users/data/models/user_model.dart';

import 'user_local_data_source_test.mocks.dart';

@GenerateMocks([Box<UserModel>])
void main() {
  late MockBox<UserModel> box;
  late UserLocalDataSource sut;

  setUp(() {
    box = MockBox();
    sut = UserLocalDataSourceObjectBoxImpl(box: box);
  });

  group('getUsers', () {

    List<UserModel> listUsers = [
      const UserModel(
          uuidString: "75c46e40-7098-11ed-9a71-a91a125af53c",
          firstName: "John",
          lastName: "Doe")
    ];
    UserListModel expectedUserListModel = UserListModel(userModels: listUsers);

    test('should return users when available in cache', () async {
      when(box.getAll()).thenReturn(listUsers);
      final result = await sut.getUsers();
      expect(result, expectedUserListModel);
    });

    test('should throw a CacheException when there is not a cached value', () {
      when(box.getAll()).thenReturn([]);
      final call = sut.getUsers;
      expect(() => call(), throwsA(const TypeMatcher<CacheException>()));
    });
  });

  group('cacheUsers', (){
    List<UserModel> listUsers = [
      const UserModel(
          uuidString: "75c46e40-7098-11ed-9a71-a91a125af53c",
          firstName: "John",
          lastName: "Doe")
    ];
    UserListModel expectedUserListModel = UserListModel(userModels: listUsers);

    test('should call the Box to store users', () async {
      when(box.putMany(any)).thenReturn([1]);
      await sut.cacheUsers(expectedUserListModel);
      verify(box.putMany(listUsers));
    });
  });
}
