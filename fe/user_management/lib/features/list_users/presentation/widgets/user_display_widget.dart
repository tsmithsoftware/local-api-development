import 'package:flutter/material.dart';
import 'package:user_management/features/list_users/domain/entities/user_entity.dart';

class UserListDisplayWidget extends StatelessWidget {
  final List<UserEntity> users;
  const UserListDisplayWidget({super.key, required this.users});

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      child: GridView.builder(
          itemCount: users.length,
          gridDelegate:
          const SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 3),
          itemBuilder: (BuildContext context, int index) {
            return UserDisplayWidget(users[index]);
          }),
    );
  }
}

class UserDisplayWidget extends StatelessWidget {
  final UserEntity user;
  const UserDisplayWidget(this.user, {super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
        padding: const EdgeInsets.fromLTRB(10, 10, 10, 0),
        height: 50,
        width: double.maxFinite,
        child: Card(
          color: Colors.tealAccent,
          elevation: 5,
          child: Row(
            children: [
              const Padding(
                padding: EdgeInsets.all(8.0),
                child: Icon(Icons.face)
              ),
              Text("${user.firstName} ${user.lastName}"),
            ],
          ),
        ));
  }
}
