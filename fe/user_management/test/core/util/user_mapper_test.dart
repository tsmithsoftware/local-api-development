import 'package:flutter_test/flutter_test.dart';
import 'package:user_management/core/util/user_mapper.dart';
import 'package:user_management/features/list_users/data/models/user_model.dart';
import 'package:user_management/features/list_users/data/objectbox/entities/user.dart';

void main() {
  late UserMapperImpl mapperImpl;
  const userModel = UserModel(lastName: "lastName", firstName: "firstName", uuidString: "uuidString");
  final user = User(guid: "uuidString", firstName: "firstName", lastName: "lastName");

  setUp(() {
    mapperImpl = UserMapperImpl();
  });

  group('testing UserMapper', () {

    test('mapUserModelToUser maps correctly', () {
      var mappedUser = mapperImpl.mapUserModelToUser(userModel);
      assert(userModel.uuidString == mappedUser.guid);
      assert(userModel.firstName == mappedUser.firstName);
      assert(userModel.lastName == mappedUser.lastName);
    });

    test('mapUserToUserModel maps correctly', () {
      var mappedUser = mapperImpl.mapUserToUserModel(user);
      assert(mappedUser.uuidString == user.guid);
      assert(mappedUser.firstName == user.firstName);
      assert(mappedUser.lastName == user.lastName);
    });
  });
}