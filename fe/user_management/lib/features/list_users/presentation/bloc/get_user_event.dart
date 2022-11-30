import 'package:equatable/equatable.dart';

abstract class UserEvent extends Equatable {
  const UserEvent(): super();
}

class GetUsersEvent extends UserEvent {
  const GetUsersEvent(): super();

  @override
  List<Object?> get props => [];
}
