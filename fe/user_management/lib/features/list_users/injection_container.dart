import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:get_it/get_it.dart';
import 'package:objectbox/objectbox.dart';
import 'package:user_management/core/util/user_mapper.dart';
import 'package:user_management/features/list_users/data/datasources/user_local_data_source.dart';
import 'package:user_management/features/list_users/data/datasources/user_remote_data_source.dart';
import 'package:user_management/features/list_users/domain/usecases/get_user_data_usecase.dart';
import 'package:user_management/features/list_users/presentation/bloc/get_user_bloc.dart';

import '../../core/platform/network_info.dart';
import '../../core/util/object_box.dart';
import 'data/objectbox/entities/user.dart';
import 'data/repositories/user_data_repository_impl.dart';
import 'domain/repositories/user_data_repository.dart';
import 'package:http/http.dart' as http;

final sl = GetIt.instance;

Future<void> init() async {
  //! Features - List Users
  //Bloc
  sl.registerFactory(
        () => GetUserBloc(useCase: sl()),
  );
  // Use cases
  sl.registerLazySingleton(() => GetUserDataUseCase(repository: sl()));
  // Repository
  sl.registerLazySingleton<UserDataRepository>(
          () => UserDataRepositoryImpl(
        remoteDataSource: sl(),
        localDataSource: sl(),
        networkInfo: sl(),
      ));
  // Data sources
  sl.registerLazySingleton<UserRemoteDataSource>(
        () => UserRemoteDataSourceImpl(client: sl()),
  );

  sl.registerLazySingleton<UserLocalDataSource>(
        () => UserLocalDataSourceObjectBoxImpl(box: sl(), mapper: sl()),
  );

  sl.registerLazySingleton<IUserMapper>(() => UserMapperImpl());

  // Core
  sl.registerLazySingleton<Connectivity>(() => Connectivity());
  sl.registerLazySingleton<NetworkInfo>(() => NetworkInfoImpl(connectivity: sl()));
  sl.registerLazySingleton(() => http.Client());
  final objectBox = await ObjectBox.create();
  sl.registerLazySingleton<Box<User>>(() => objectBox.store.box<User>());
}