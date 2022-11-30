import 'package:objectbox/objectbox.dart';
import 'package:user_management/core/error/exception.dart';
import 'package:user_management/features/list_users/data/models/user_list_model.dart';

import '../models/user_model.dart';

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
  final Box<UserModel> box;

  UserLocalDataSourceObjectBoxImpl({required this.box});

  @override
  Future<void> cacheUsers(UserListModel users) async {
    box.putMany(users.userModels);
    return Future.value();
  }

  @override
  Future<UserListModel> getUsers() async {
    List<UserModel> users = box.getAll();
    if(users.isNotEmpty) {
      return UserListModel(userModels: users);
    } else {
      throw CacheException();
    }
  }
}