import 'package:user_management/features/list_users/domain/entities/user_entity.dart';

class UserModel extends UserEntity {
  const UserModel({required lastName, required firstName, required uuidString})
      : super(lastName: lastName, firstName: firstName, uuidString: uuidString);

  factory UserModel.fromJson(Map<String, dynamic> jsonMap) {
    return UserModel(
        firstName: jsonMap['firstName'],
        lastName: jsonMap['lastName'],
        uuidString: jsonMap['uuid']);
  }

  Map<String, dynamic> toJson() {
    return {
      "lastName": lastName,
      "firstName": firstName,
      "uuid": uuidString
    };
  }
}
