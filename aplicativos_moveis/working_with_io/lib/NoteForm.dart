import 'package:flutter/material.dart';
import 'package:working_with_io/note.dart';

class NoteForm extends StatefulWidget {
  final Note note;

  const NoteForm({super.key, required this.note});

  @override
  State<StatefulWidget> createState() {
    return NoteFormState(note);
  }
}

class NoteFormState extends State<NoteForm> {
  NoteFormState(this.note);

  final Note note;

  @override
  Widget build(BuildContext context) {
    var idController = TextEditingController(text: "${note.id}");
    var titleController = TextEditingController(text: note.title);
    var textController = TextEditingController(text: note.text);

    void _onPressed() {
      note.id = int.parse(idController.text);
      note.title = titleController.text;
      note.text = textController.text;

      Navigator.pop(context, note);
    }

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text("Form"),
      ),
      body: Container(
        padding: EdgeInsets.all(10),
        child: Column(
          children: [
            Column(
              children: [
                const Text("Id:"),
                TextFormField(
                  controller: idController,
                  enabled: false,
                  textAlign: TextAlign.center,
                )
              ],
            ),
            const SizedBox(height: 10),
            Column(
              children: [
                const Text("Title:"),
                TextFormField(
                  controller: titleController,
                )
              ],
            ),
            const SizedBox(height: 10),
            Column(
              children: [
                const Text("Text:"),
                TextFormField(
                  maxLines: 5,
                  controller: textController,
                )
              ],
            ),
            const SizedBox(height: 20),
            ElevatedButton(onPressed: _onPressed, child: const Text("Save"))
          ],
        ),
      ),
    );
  }
}
