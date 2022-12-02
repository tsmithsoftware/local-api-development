import 'package:connectivity_plus/connectivity_plus.dart';

abstract class NetworkInfo {
  Future<bool> get isConnected;
}

class NetworkInfoImpl extends NetworkInfo {
  Connectivity connectivity;
  NetworkInfoImpl({required this.connectivity});

  @override
  Future<bool> get isConnected async {
    return await connectivity.checkConnectivity()
        != ConnectivityResult.none;
  }
}
