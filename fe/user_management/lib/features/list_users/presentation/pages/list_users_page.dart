import 'package:flutter/cupertino.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:user_management/features/list_users/presentation/bloc/get_user_event.dart';
import 'package:user_management/features/list_users/presentation/bloc/get_user_states.dart';

import '../../../../injection_container.dart';
import '../bloc/get_user_bloc.dart';

class ListUsersPage extends StatelessWidget {
  const ListUsersPage({super.key});

  static const String id = "/ListUsersPage";

  @override
  Widget build(BuildContext context) {
    sl<GetUserBloc>().add(const GetUsersEvent());
    return BlocProvider.value(
      value: sl<GetUserBloc>(),
      child: BlocBuilder<GetUserBloc, GetUsersState>(
        builder: (context, state) {
          if(state is GetUsersEmpty) {
            return const Text("GetUsrs Empty");
          } else if (state is GetUsersLoading) {
            return const Text("Loading!");
          } else if (state is GetUsersLoaded) {
            return const Text("Loaded!");
          }
          return Container();
        }
      ),
    );
  }
}