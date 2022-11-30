import 'dart:convert';

import 'package:flutter_test/flutter_test.dart';
import 'package:user_management/features/list_users/data/models/user_list_model.dart';
import 'package:user_management/features/list_users/data/models/user_model.dart';
import 'package:uuid_type/uuid_type.dart';

import '../../../../fixtures/fixture_reader.dart';

void main() {
  final users = [
    const UserModel(lastName: "Doe", firstName: "John", uuidString: "6ba7b811-9dad-11d1-80b4-00c04fd430c8"),
    const UserModel(lastName: "Smith", firstName: "Danny", uuidString: "f91728f0-7218-44fc-8cb6-554b33b4af8d"),
    const UserModel(lastName: "Smith", firstName: "Harry", uuidString: "47c3f3ad-16dc-445f-bcc0-086c046c5d84")
  ];

  final tUserListModel = UserListModel(userModels: users);

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