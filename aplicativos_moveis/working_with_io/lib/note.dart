import 'package:dson/dson.dart';

@serializable
class Note {
  int? id;
  String? title;
  String? text;

  Note({this.id, this.title, this.text});

  Map<String, dynamic> toMap() {
    return {
      "id": this.id,
      "title": this.title,
      "text": this.text,
    };
  }

  static Note fromMap(Map<String, dynamic> map) {
    return Note(id: map['id'] as int, title: map['title'], text: map['text']);
  }
}
