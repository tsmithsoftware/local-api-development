import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:user_management/features/list_users/presentation/bloc/get_user_event.dart';
import 'package:user_management/features/list_users/presentation/bloc/get_user_states.dart';
import 'package:user_management/features/list_users/presentation/widgets/error_display_widget.dart';
import 'package:user_management/features/list_users/presentation/widgets/page_loading_widget.dart';
import 'package:user_management/features/list_users/presentation/widgets/user_display_widget.dart';

import '../bloc/get_user_bloc.dart';

class ListUsersPage extends StatelessWidget {
  const ListUsersPage({super.key});

  static const String id = "/ListUsersPage";

  @override
  Widget build(BuildContext context) {
    return Container(
      height: MediaQuery.of(context).size.height,
      width: MediaQuery.of(context).size.width,
      color: Colors.white,
      child: RefreshIndicator(
          onRefresh: () => getUsers(context),
          child: BlocBuilder<GetUserBloc, GetUsersState>(
            bloc:  BlocProvider.of<GetUserBloc>(context),
            builder: (context, state) {
              if(state is GetUsersEmpty) {
                getUsers(context);
                return Container();
              } else if (state is GetUsersLoading) {
                return const PageLoadingWidget();
              } else if (state is GetUsersLoaded) {
                return UserListDisplayWidget(users: state.users.users);
              } else if (state is GetUsersError) {
                return ErrorDisplayWidget(state.message);
              } else {
                return Container();
              }
            }
          ),
        ),
    );
  }

  Future<void> getUsers(BuildContext context) async {
    return BlocProvider.of<GetUserBloc>(context).add(const GetUsersEvent());
  }
}