import 'package:user_management/features/list_users/domain/entities/user_entity.dart';

class UserModel extends UserEntity {
  const UserModel({required lastName, required firstName})
      : super(lastName: lastName, firstName: firstName);

  factory UserModel.fromJson(Map<String, dynamic> jsonMap) {
    return UserModel(
        firstName: jsonMap['firstName'], lastName: jsonMap['lastName']);
  }

  Map<String, dynamic> toJson() {
    return {
      "lastName": lastName,
      "firstName": firstName
    };
  }
}
