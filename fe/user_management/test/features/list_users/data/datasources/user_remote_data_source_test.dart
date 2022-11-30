import 'package:http/http.dart' as http;
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';

@GenerateMocks([http.Client])
void main() {

  setUp((){
  });

  group('getUsers', (){
    test('should call the endpoint with the correct headers', () async {
      //await _remoteDataSource.getUsers();

    });
  });
}