import 'package:user_management/features/list_users/data/models/user_model.dart';

import '../../features/list_users/data/objectbox/entities/user.dart';

abstract class IUserMapper {
  UserModel mapUserToUserModel(User user);
  User mapUserModelToUser(UserModel model);
}

///
/// Utility class to map the UserModel used for over-the-wire transmission
/// to the User class used in the ObjectBox
///
class UserMapperImpl extends IUserMapper {
  @override
  User mapUserModelToUser(UserModel model) {
    return User(
      guid: model.uuidString,
      firstName: model.firstName,
      lastName: model.lastName
    );
  }

  @override
  UserModel mapUserToUserModel(User user) {
    return UserModel(
      uuidString: user.guid,
      firstName: user.firstName,
      lastName: user.lastName
    );
  }
}