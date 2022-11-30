import 'package:equatable/equatable.dart';

import '../../domain/entities/user_list_entity.dart';

abstract class GetUsersState extends Equatable {
  const GetUsersState([List props = const <dynamic>[]]) : super();
}

class GetUsersEmpty extends GetUsersState {
  @override
  List<Object?> get props => [];
}

class GetUsersLoading extends GetUsersState {
  @override
  List<Object?> get props => [];
}

class GetUsersLoaded extends GetUsersState {
  final UserListEntity users;

  GetUsersLoaded({required this.users}) : super([users]);

  @override
  List<Object?> get props => [users];
}

class GetUsersError extends GetUsersState {
  final String message;

  GetUsersError({required this.message}) : super([message]);

  @override
  List<Object?> get props => [message];
}