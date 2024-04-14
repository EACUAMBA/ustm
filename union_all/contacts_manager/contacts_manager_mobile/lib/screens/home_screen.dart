import 'package:contacts_manager_mobile/backend_api_service.dart';
import 'package:contacts_manager_mobile/screens/contact_form.dart';
import 'package:flutter/material.dart';

import '../models/contact.dart';

class HomeScreen extends StatefulWidget {
  final String title;

  const HomeScreen({super.key, required this.title});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  List<Contact> contacts = List.empty(growable: true);

  @override
  void initState() {
    super.initState();
    _loadData();
    print("initState");
  }

  Future<void> _loadData() async {
    contacts =
    (await ContactsApiService.getInstance()?.findAll()) as List<Contact>;
    setState(() {});
  }

  Future<void> _onRefresh() async {
    contacts =
        (await ContactsApiService.getInstance()?.findAll()) as List<Contact>;
    setState(() {});
  }

  void onPressedCreateContact() async {
    final result = await Navigator.of(context).push(MaterialPageRoute(
        builder: (context) => const ContactForm("Create Contact", true)));

    if (!context.mounted) return;

    if (result != null) {
      await _onRefresh();
    }
  }

  Future<void> _onPressedEdit(Contact contact) async {
    final result = await Navigator.of(context).push(MaterialPageRoute(
      builder: (context) => const ContactForm("Edit Contact", false),
      settings: RouteSettings(
        arguments: contact,
      ),
    ));

    if (!context.mounted) return;


      await _onRefresh();

  }

  Future<void> _onPressedDelete(Contact contact) async {
    await ContactsApiService.getInstance()?.delete(contact);
    await _onRefresh();
  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: RefreshIndicator(
        onRefresh: _onRefresh,
        child: Container(
          margin: const EdgeInsets.all(10),
          child: ListView.builder(
            itemCount: contacts.length,
            itemBuilder: (context, index) {
              final con = contacts[index];
              return Container(
                child: ListTile(
                  title: Text(con.name),
                  subtitle: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [Text(con.number), Text(con.email)],
                  ),
                  trailing: Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      IconButton(
                          onPressed: () => _onPressedEdit(con),
                          icon: Icon(Icons.edit)),
                      IconButton(
                          onPressed: () => _onPressedDelete(con),
                          icon: Icon(Icons.delete))
                    ],
                  ),
                ),
              );
            },
          ),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: onPressedCreateContact,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}
