import 'package:get_it/get_it.dart';
import 'features/list_users/injection_container.dart' as list_users_container;

final sl = GetIt.instance;

void init() {
  //! Features - ListUsers
  list_users_container.init();

}