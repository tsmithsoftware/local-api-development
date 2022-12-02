import 'package:objectbox/objectbox.dart';
import 'package:user_management/core/error/exception.dart';
import 'package:user_management/features/list_users/data/models/user_list_model.dart';

import '../../../../core/util/user_mapper.dart';
import '../models/user_model.dart';
import '../objectbox/entities/user.dart';

abstract class UserLocalDataSource {
  ///
  /// Gets the last cached set of users
  /// Throws [CacheException] if no users stored
  Future<UserListModel> getUsers();

  ///
  /// Caches a set of users
  ///
  Future<void> cacheUsers(UserListModel users);
}

class UserLocalDataSourceObjectBoxImpl extends UserLocalDataSource {
  final Box<User> box;
  final IUserMapper mapper;

  UserLocalDataSourceObjectBoxImpl({required this.box, required this.mapper});

  @override
  Future<void> cacheUsers(UserListModel users) async {
    List<User> storedUsers = users.userModels
        .map((userModel) => mapper.mapUserModelToUser(userModel))
        .toList();
    box.putMany(storedUsers);
    return Future.value();
  }

  @override
  Future<UserListModel> getUsers() async {
    List<User> users = box.getAll();
    List<UserModel> userModels = users
        .map((user) => mapper.mapUserToUserModel(user))
        .toList();

    if (userModels.isNotEmpty) {
      return UserListModel(userModels: userModels);
    } else {
      throw CacheException();
    }
  }
}
