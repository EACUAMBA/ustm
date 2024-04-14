import 'package:contacts_manager_mobile/backend_api_service.dart';
import 'package:flutter/material.dart';

import '../models/contact.dart';

class ContactForm extends StatefulWidget {
  final String title;
  final bool isCreate;

  const ContactForm(this.title, this.isCreate, {super.key});

  @override
  State<StatefulWidget> createState() => _ContactFormState();
}

class _ContactFormState extends State<ContactForm> {
  final nameController = TextEditingController();
  final numberController = TextEditingController();
  final emailController = TextEditingController();

  int id = -1;

  void save(BuildContext context) async {
    final contact = Contact(
        nameController.text, numberController.text, emailController.text);

    CreateOrUpdateResponse? createOrUpdateResponse =
        await ContactsApiService.getInstance()?.create(contact);

    if (context.mounted && createOrUpdateResponse != null) {
      contact.id = createOrUpdateResponse.id;
      Navigator.pop(context, contact);
    }
  }

  void update(BuildContext context) async {
    final contact = Contact(nameController.text, numberController.text, emailController.text, id: this.id);

    CreateOrUpdateResponse? createOrUpdateResponse =
        await ContactsApiService.getInstance()?.update(contact);

    if (context.mounted && createOrUpdateResponse != null) {
      contact.id = createOrUpdateResponse.id;
      Navigator.pop(context);
    }
  }

  @override
  void dispose() {
    nameController.dispose();
    numberController.dispose();
    emailController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    if(!widget.isCreate){
    final contact = ModalRoute.of(context)!.settings.arguments as Contact;
      this.id = contact.id!;
      this.nameController.text = contact.name;
      this.numberController.text = contact.number;
      this.emailController.text = contact.email;
    }

    return Scaffold(
        resizeToAvoidBottomInset: false,
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: Text(widget.title),
        ),
        body: Padding(
          padding: const EdgeInsets.all(10),
          child: Column(
            children: [
              Container(
                  margin: const EdgeInsets.only(top: 30),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      const Text("Name"),
                      TextField(
                        controller: nameController,
                        decoration: const InputDecoration(hintText: "Name"),
                      )
                    ],
                  )),
              Container(
                  margin: const EdgeInsets.only(top: 30),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      const Text("Number"),
                      TextField(
                        controller: numberController,
                        decoration: const InputDecoration(hintText: "Number"),
                      )
                    ],
                  )),
              Container(
                margin: const EdgeInsets.only(top: 30),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text("Email"),
                    TextField(
                      controller: emailController,
                      decoration: const InputDecoration(hintText: "Email"),
                    )
                  ],
                ),
              ),
              Container(
                margin: const EdgeInsets.only(top: 100),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  children: [
                    ElevatedButton(
                        onPressed: () => {Navigator.pop(context)},
                        child: Text("Cancel")),
                    widget.isCreate
                        ? ElevatedButton(
                            onPressed: () => save(context), child: Text("Save"))
                        : ElevatedButton(
                            onPressed: () => update(context),
                            child: Text("Update"))
                  ],
                ),
              )
            ],
          ),
        ));
  }
}
