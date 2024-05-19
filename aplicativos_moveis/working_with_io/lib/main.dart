import 'package:flutter/material.dart';
import 'package:working_with_io/DatabaseService.dart';
import 'package:working_with_io/NoteView.dart';

import 'NoteForm.dart';
import 'note.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Notes',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const NotesList(title: 'Notes App'),
    );
  }
}

class NotesList extends StatefulWidget {
  const NotesList({super.key, required this.title});

  final String title;

  @override
  State<NotesList> createState() => _NotesListState();
}

class _NotesListState extends State<NotesList> {
  List<Note> noteList = List.empty(growable: true);

  @override
  Widget build(BuildContext context) {
    void onPressedGoToNoteForm() async {
      final result =
          await Navigator.push(context, MaterialPageRoute(builder: (context) {
        var note = Note();
        note.id = DateTime.now().millisecondsSinceEpoch;
        return NoteForm(note: note);
      }));

      if (!context.mounted) return;

      if (result != null) {
        await DatabaseService.addNote(result as Note);

        List<Note> notes = await DatabaseService.getAllNotes();

        setState(
          () {
            noteList = notes;
          },
        );
      } else {
        List<Note> notes = await DatabaseService.getAllNotes();

        setState(
          () {
            noteList = notes;
          },
        );
      }
    }

    void onPressedGoToNoteView(Note note) async {
      await Navigator.push(context, MaterialPageRoute(builder: (context) {
        return NoteView(note: note);
      }));
    }

    Widget? createRow(BuildContext context, int index) {
      Note note = noteList[index];

      return InkWell(
        onTap: () => onPressedGoToNoteView(note),
        child: Row(
          children: [
            Text("${note.id}"),
            const SizedBox(
              width: 15,
            ),
            Text(note.title!),
            InkWell(
              child: Icon(Icons.delete),
            )
          ],
        ),
      );
    }

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: ListView.builder(
        itemBuilder: createRow,
        itemCount: noteList.length,
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: onPressedGoToNoteForm,
        tooltip: 'Add',
        child: const Icon(Icons.add),
      ),
    );
  }
}
