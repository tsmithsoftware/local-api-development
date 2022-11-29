import 'dart:convert';

import 'package:flutter_test/flutter_test.dart';
import 'package:user_management/features/list_users/data/models/user_model.dart';

import '../../../../fixtures/fixture_reader.dart';

void main() {
  const tUserModel = UserModel(lastName: "Doe", firstName: "John");

  group('fromJson', (){
    test('should return a valid model from JSON', (){
      final Map<String, dynamic> jsonMap =
      json.decode(fixture('user.json'));
      final result = UserModel.fromJson(jsonMap);
      expect(result, tUserModel);
    });
  });

  group('toJson', () {
    test(
      'should return a JSON map containing the proper data',
          () async {
        // act
        final result = tUserModel.toJson();
        // assert
        final Map<String, dynamic> expectedJsonMap =
        json.decode(fixture('user.json'));

        expect(result, expectedJsonMap);
      },
    );
  });
}