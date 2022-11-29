import 'dart:convert';

import 'package:flutter_test/flutter_test.dart';
import 'package:user_management/features/list_users/data/models/user_list_model.dart';
import 'package:user_management/features/list_users/data/models/user_model.dart';

import '../../../../fixtures/fixture_reader.dart';

void main() {
  const users = [
    UserModel(lastName: "Doe", firstName: "John"),
    UserModel(lastName: "Smith", firstName: "Danny"),
    UserModel(lastName: "Smith", firstName: "Harry")
  ];
  const tUserListModel = UserListModel(users: users);

  group('fromJson', (){
    test('should return a valid model from JSON', () async {
      final Map<String, dynamic> jsonMap =
      json.decode(fixture('user_list.json'));
      final result = UserListModel.fromJson(jsonMap);
      expect(result, tUserListModel);
    });
  });

  group('toJson', (){
    test(
        'should return a JSON map containing the proper data',
            () async {
              final result = tUserListModel.toJson();
              final Map<String, dynamic> expectedJsonMap =
              json.decode(fixture('user_list.json'));
              expect(result, expectedJsonMap);
    });
  });
}