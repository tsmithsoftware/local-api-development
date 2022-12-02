import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:objectbox/objectbox.dart';
import 'package:user_management/core/error/exception.dart';
import 'package:user_management/core/util/user_mapper.dart';
import 'package:user_management/features/list_users/data/datasources/user_local_data_source.dart';
import 'package:user_management/features/list_users/data/models/user_list_model.dart';
import 'package:user_management/features/list_users/data/models/user_model.dart';
import 'package:user_management/features/list_users/data/objectbox/entities/user.dart';

import 'user_local_data_source_test.mocks.dart';

@GenerateMocks([Box<User>, IUserMapper])
void main() {
  late MockBox<User> box;
  late UserLocalDataSource sut;
  late MockIUserMapper mapper;
  late List<User> listUsers;
  late UserListModel expectedUserListModel;

  setUpAll(() {
    box = MockBox();
    mapper = MockIUserMapper();
    sut = UserLocalDataSourceObjectBoxImpl(box: box, mapper: mapper);
    User user = User(
        guid: "75c46e40-7098-11ed-9a71-a91a125af53c",
        firstName: "John",
        lastName: "Doe");

    listUsers = [user];

    UserModel userModel = const UserModel(
        uuidString: "75c46e40-7098-11ed-9a71-a91a125af53c",
        firstName: "John",
        lastName: "Doe");

    List<UserModel> listUserModels = [userModel];
    expectedUserListModel =
    UserListModel(userModels: listUserModels);

    when(mapper.mapUserToUserModel(user)).thenReturn(userModel);
    when(mapper.mapUserModelToUser(userModel)).thenReturn(user);
  });

  group('getUsers', () {
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

  group('cacheUsers', () {
    final user = User(
        guid: "75c46e40-7098-11ed-9a71-a91a125af53c",
        firstName: "John",
        lastName: "Doe");

    List<User> listUsers = [
      user
    ];

    const userModel = UserModel(
        uuidString: "75c46e40-7098-11ed-9a71-a91a125af53c",
        firstName: "John",
        lastName: "Doe");

    List<UserModel> listUserModels = [
      userModel
    ];

    UserListModel expectedUserListModel =
        UserListModel(userModels: listUserModels);

    test('should call the Box to store users', () async {
      when(box.putMany(any)).thenReturn([1]);
      when(mapper.mapUserModelToUser(userModel)).thenReturn(user);
      await sut.cacheUsers(expectedUserListModel);
      verify(box.putMany(listUsers));
    });

    test('two users with the same name but different uuids will be unique',
        () async {
      User userOne = User(
          guid: "75c46e40-7098-11ed-9a71-a91a125af53c",
          firstName: "John",
          lastName: "Doe");

      User userTwo = User(
          guid: "f91728f0-7218-44fc-8cb6-554b33b4af8d",
          firstName: "John",
          lastName: "Doe");

      List<User> userList = [
        userOne,
        userTwo
      ];

      UserModel userModelOne = const UserModel(
          uuidString: "75c46e40-7098-11ed-9a71-a91a125af53c",
          firstName: "John",
          lastName: "Doe");

      UserModel userModelTwo = const UserModel(
          uuidString: "f91728f0-7218-44fc-8cb6-554b33b4af8d",
          firstName: "John",
          lastName: "Doe");

      List<UserModel> userModelList = [
        userModelOne,
        userModelTwo
      ];

      UserListModel userListModel =
          UserListModel(userModels: userModelList);

      when(mapper.mapUserModelToUser(userModelOne)).thenReturn(userOne);
      when(mapper.mapUserModelToUser(userModelTwo)).thenReturn(userTwo);

      when(mapper.mapUserToUserModel(userOne)).thenReturn(userModelOne);
      when(mapper.mapUserToUserModel(userTwo)).thenReturn(userModelTwo);

      when(box.getAll()).thenReturn(userList);
      when(box.putMany(any)).thenReturn([1, 2]);

      await sut.cacheUsers(userListModel);
      verify(box.putMany(userList));
      UserListModel result = await sut.getUsers();
      expect(result, equals(userListModel));
    });
  });
}
