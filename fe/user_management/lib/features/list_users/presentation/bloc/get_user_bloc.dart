import 'package:user_management/features/list_users/presentation/bloc/get_user_states.dart';
import 'package:bloc/bloc.dart';

import '../../../../core/usecase.dart';
import '../../domain/usecases/get_user_data_usecase.dart';
import 'get_user_event.dart';

class GetUserBloc extends Bloc<GetUsersEvent, GetUsersState> {
  final GetUserDataUseCase useCase;
  GetUserBloc({required this.useCase}) : super(GetUsersEmpty()) {
    on<GetUsersEvent>((event, emit) => mapEventToState(event, emit));
  }

  mapEventToState(GetUsersEvent event, Emitter<GetUsersState> emit) async {
    emit(GetUsersEmpty());
    emit(GetUsersLoading());
    final result = await useCase.call(NoParams());
    result.fold(
        (failure) => emit(GetUsersError(message: failure.message)),
        (users) => emit(GetUsersLoaded(users: users)));
  }
}
