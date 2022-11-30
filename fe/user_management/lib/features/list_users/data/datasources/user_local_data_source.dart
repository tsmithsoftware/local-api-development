import 'package:user_management/features/list_users/data/models/user_list_model.dart';

abstract class UserLocalDataSource {
  ///
  /// Gets the last cached set of users
  /// Throws [NoLocalDataException] if no users stored
  Future<UserListModel> getUsers();
  ///
  /// Caches a set of users
  ///
  Future<void> cacheUsers(UserListModel users);
}

class UserLocalDataSourceObjectBoxImpl extends UserLocalDataSource {
  @override
  Future<void> cacheUsers(UserListModel users) {
    // TODO: implement cacheUsers
    throw UnimplementedError();
  }

  @override
  Future<UserListModel> getUsers() {
    // TODO: implement getUsers
    throw UnimplementedError();
  }
}