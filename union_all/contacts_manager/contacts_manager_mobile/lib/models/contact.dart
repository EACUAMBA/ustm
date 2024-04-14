import 'dart:typed_data';

class Contact {
  int? id;
  String name;
  String number;
  String email;

  Contact(this.name, this.number, this.email, {this.id});

  @override
  String toString() {
    return 'Contact{id: $id, name: $name, number: $number, email: $email}';
  }

  factory Contact.fromJson(dynamic json) {
    return switch (json) {
      {
      'id': int? id,
      'name': String name,
      'number': String number,
      'email': String email,
      } => Contact(name, number, email, id: id),
      _ => throw const FormatException('Failed to load album.'),
    };
  }

  Map<String, dynamic> toJson() {
    return
      <String, dynamic>{
      'id': id,
      'name': name,
      'number':  number,
      'email':  email,
      };
  }
}
