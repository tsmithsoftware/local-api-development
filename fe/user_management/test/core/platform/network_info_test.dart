import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:user_management/core/platform/network_info.dart';

import 'network_info_test.mocks.dart';

@GenerateNiceMocks([MockSpec<Connectivity>()])
void main() {
  late NetworkInfoImpl networkInfoImpl;
  late MockConnectivity mockConnectivity;

  setUp(() {
    mockConnectivity = MockConnectivity();
    networkInfoImpl = NetworkInfoImpl(connectivity: mockConnectivity);
  });

  group('isConnected', () {
    test(
      'should forward the call to DataConnectionChecker.hasConnection',
          () async {
        when(mockConnectivity.checkConnectivity())
            .thenAnswer((_) async => ConnectivityResult.wifi);
        final result = await networkInfoImpl.isConnected;
        verify(mockConnectivity.checkConnectivity());
        expect(result, true);
      },
    );
  });
}