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