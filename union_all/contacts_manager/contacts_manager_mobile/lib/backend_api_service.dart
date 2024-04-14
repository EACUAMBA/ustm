import 'dart:convert';

import 'package:contacts_manager_mobile/models/contact.dart';
import 'package:http/http.dart' as http;

class ContactsApiService {
  final Uri _uri =
      Uri(scheme: "http", port: 6999, host: "192.168.3.69", path: "contacts");

  //Singleton Design Pattern
  static ContactsApiService? _backendApiService;

  static ContactsApiService? getInstance() {
    if (_backendApiService == null) {
      return _backendApiService = ContactsApiService();
    }
    return _backendApiService;
  }

  Future<bool> delete(Contact contact) async {
    try {
      var response = await http.delete(_uri.resolveUri(Uri(path: "${_uri.path}/${contact.id}")),
          headers: <String, String>{
            'Content-Type': 'application/json; charset=UTF-8',
          });

      if (response.statusCode == 200) {
        return true;
      }
      return false;
    } on Exception catch (e) {
      //avoid log in prod, use a lib to know if id develop or not.
      print("Exception during delete contact. $e");
      return false;
    }
  }

  Future<CreateOrUpdateResponse?> create(Contact contact) async {
    try {
      var body = (await http.post(_uri,
              headers: <String, String>{
                'Content-Type': 'application/json; charset=UTF-8',
              },
              body: jsonEncode(contact.toJson())))
          .body;
      return CreateOrUpdateResponse.fromJson(
          jsonDecode(body) as Map<String, dynamic>);
    } on Exception catch (e) {
      //avoid log in prod, use a lib to know if id develop or not.
      print("Exception during create contact. $e");
      return null;
    }
  }

  Future<Iterable<Contact>?> findAll() async {
    try {
      var body = (await http.get(_uri, headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      }))
          .body;

      return (jsonDecode(body) as List)
          .map((e) => Contact.fromJson(e))
          .toList();
    } on Exception catch (e) {
      //avoid log in prod, use a lib to know if id develop or not.
      print("Exception during findAll contact. $e");
      return List.empty();
    }
  }

  Future<CreateOrUpdateResponse?> update(Contact contact) async {
    try {
      var body = (await http.put(_uri.resolveUri(Uri(path: "${_uri.path}/${contact.id}")),
          headers: <String, String>{
            'Content-Type': 'application/json; charset=UTF-8',
          },
          body: jsonEncode(contact.toJson())))
          .body;
      return CreateOrUpdateResponse.fromJson(
          jsonDecode(body) as Map<String, dynamic>);
    } on Exception catch (e) {
      //avoid log in prod, use a lib to know if id develop or not.
      print("Exception during update contact. $e");
      return null;
    }
  }
}

class CreateOrUpdateResponse {
  final int id;

  const CreateOrUpdateResponse({required this.id});

  factory CreateOrUpdateResponse.fromJson(Map<String, dynamic> json) {
    return switch (json) {
      {
        'id': int id,
      } =>
        CreateOrUpdateResponse(id: id),
      _ =>
        throw const FormatException('Failed to load CreateOrUpdateResponse.'),
    };
  }
}
