
import '../models/user_list_model.dart';

abstract class UserRemoteDataSource {
  /// Calls the getUsers endpoint to retrieve all users in the system
  ///
  /// Throws a [ServerException] for all error codes
  Future<UserListModel> getUsers();
}