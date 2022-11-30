import 'package:objectbox/objectbox.dart';

@Entity()
class User {
  int id;
  String guid;
  String firstName;
  String lastName;

  User(
      {this.id = 0,
      required this.guid,
      required this.firstName,
      required this.lastName});
}
