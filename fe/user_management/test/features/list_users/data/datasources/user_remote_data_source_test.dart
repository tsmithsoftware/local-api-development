import 'dart:convert';
import 'dart:io';

import 'package:http/http.dart' as http;
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:user_management/core/constants.dart';
import 'package:user_management/core/error/exception.dart';
import 'package:user_management/features/list_users/data/datasources/user_remote_data_source.dart';
import 'package:user_management/features/list_users/data/models/user_list_model.dart';

import '../../../../fixtures/fixture_reader.dart';
import 'user_remote_data_source_test.mocks.dart';

@GenerateMocks([http.Client])
void main() {
  late MockClient client;
  late UserRemoteDataSourceImpl remoteDataSourceImpl;

  setUp((){
    client = MockClient();
    remoteDataSourceImpl = UserRemoteDataSourceImpl(client: client);
  });

  void setUpMockHttpClientSuccess200() {
    when(client.get(any, headers: anyNamed('headers'))).thenAnswer(
            (_) async => http.Response(fixture("user_list.json"), 200)
    );
  }

  void setUpMockHttpClientFailure404() {
    when(client.get(any, headers: anyNamed('headers'))).thenAnswer(
          (_) async => http.Response('Something went wrong', 404),
    );
  }

  group('getUsers', (){
    final tUserListModel = UserListModel.fromJson(json.decode(fixture('user_list.json')));

    test('should call the endpoint with the correct headers', () async {
      setUpMockHttpClientSuccess200();
      await remoteDataSourceImpl.getUsers();
      Uri endpoint = Uri.parse(getUsersUrl);
      verify(client.get(endpoint, headers: { HttpHeaders.contentTypeHeader: ContentType.json.toString() }));
    });

    test('should return UserListModel when response is 200', () async {
      setUpMockHttpClientSuccess200();
      final result = await remoteDataSourceImpl.getUsers();
      expect(result, equals(tUserListModel));
    });

    test(
      'should throw a ServerException when the response code is 404 or other',
          () async {
        setUpMockHttpClientFailure404();
        final call = remoteDataSourceImpl.getUsers;
        expect(() => call(), throwsA(const TypeMatcher<ServerException>()));
      },
    );
  });
}