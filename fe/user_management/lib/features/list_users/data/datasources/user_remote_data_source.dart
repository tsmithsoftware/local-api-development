
import '../models/user_list_model.dart';
import 'package:http/http.dart' as http;

abstract class UserRemoteDataSource {
  /// Calls the getUsers endpoint to retrieve all users in the system
  ///
  /// Throws a [ServerException] for all error codes
  Future<UserListModel> getUsers();
}

class UserRemoteDataSourceImpl extends UserRemoteDataSource {
  final http.Client client;

  UserRemoteDataSourceImpl({required this.client});

  @override
  Future<UserListModel> getUsers() {
    // TODO: implement getUsers
    throw UnimplementedError();
  }
}