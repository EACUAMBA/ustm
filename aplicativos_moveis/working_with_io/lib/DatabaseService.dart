import 'dart:convert';
import 'dart:io';

import 'package:path_provider/path_provider.dart';

import 'note.dart';

class DatabaseService{

  static Future<File> getFile() async {
    final Directory? downloadsDir = await getDownloadsDirectory();
    return File("${downloadsDir?.path}/database.json");
  }

  static Future<List<Note>> getAllNotes() async {
    File file = await getFile();

    String data = await file.readAsString();

    return (jsonDecode(data) as List<dynamic>)
        .map((e) => e as Map<String, dynamic>)
        .map((e) => Note.fromMap(e))
        .toList(growable: true);
  }

  static Future<void> addNote(Note note) async {
    File file = await getFile();

    try {
      String data = await file.readAsString();
      List<Note> noteList = (jsonDecode(data) as List<dynamic>)
          .map((e) => e as Map<String, dynamic>)
          .map((e) => Note.fromMap(e))
          .toList(growable: true);

      noteList.add(note);

      List<Map<String, dynamic>> listOfMaps =
      noteList.map((e) => e.toMap()).toList(growable: true);

      await file.writeAsString(jsonEncode(listOfMaps));
    } on Exception {
      List<Map<String, dynamic>> noteList =
      [note].map((e) => e.toMap()).toList(growable: true);
      await file.writeAsString(jsonEncode(noteList));
    }
  }

}