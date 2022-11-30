import 'package:user_management/features/list_users/data/models/user_model.dart';
import 'package:user_management/features/list_users/domain/entities/user_list_entity.dart';

class UserListModel extends UserListEntity {
  const UserListModel({required users}): super(users);

  factory UserListModel.fromJson(Map<String, dynamic> jsonMap) {
    return UserListModel(
        users: jsonMap['users']
            .map<UserModel>((model) =>
            UserModel(
                lastName: model["lastName"],
                firstName: model["firstName"],
              uuidString: model['uuid']
            )).toList() );
  }

  Map<String, dynamic> toJson() {
    return {
      'users': users.map((e) => (e as UserModel).toJson()).toList()
    };
  }
}