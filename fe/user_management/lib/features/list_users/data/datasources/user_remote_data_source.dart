import 'dart:convert';
import 'dart:io';

import 'package:user_management/core/constants.dart';

import '../../../../core/error/exception.dart';
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
  Future<UserListModel> getUsers() async {
    Uri endpoint = Uri.parse(getUsersUrl);
    final response = await client.get(endpoint,
        headers: {HttpHeaders.contentTypeHeader: ContentType.json.toString()});

    if (response.statusCode == 200) {
      return UserListModel.fromJson(json.decode(response.body));
    } else {
      throw ServerException();
    }
  }
}
