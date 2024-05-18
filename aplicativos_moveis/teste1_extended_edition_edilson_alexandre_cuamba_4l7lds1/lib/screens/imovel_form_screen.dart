import 'package:flutter/material.dart';

import '../models/imovel.dart';

class ImovelFormScreen extends StatefulWidget {
  final Imovel imovel;

  const ImovelFormScreen({super.key, required this.imovel});

  @override
  State<StatefulWidget> createState() {
    return BilheteFormState(imovel);
  }
}

class BilheteFormState extends State<ImovelFormScreen> {
  BilheteFormState(this.imovel);

  final Imovel imovel;
  String? tipo;

  @override
  Widget build(BuildContext context) {
    var idc = TextEditingController(text: "${imovel.id}");
    var nrc = TextEditingController(text: imovel.nr);
    var locc = TextEditingController(text: imovel.loc);
    var imgUrlc = TextEditingController(text: imovel.imgUrl);

    void _save() {
      this.imovel.nr = nrc.value.text;
      this.imovel.tipo = tipo;
      this.imovel.loc = locc.value.text;
      this.imovel.imgUrl = imgUrlc.value.text;

      Navigator.pop(context, this.imovel);
    }

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text("Formulário"),
      ),
      body: Column(
        children: [
          Column(
            children: [
              Text("Id"),
              TextField(
                controller: idc,
                enabled: false,
              )
            ],
          ),
          Column(
            children: [
              Text("Numero"),
              TextField(
                controller: nrc,
              )
            ],
          ),
          Column(
            children: [
              Text("Tipo"),
              DropdownButton<String>(
                value: tipo,
                icon: const Icon(Icons.arrow_downward),
                elevation: 16,
                style: const TextStyle(color: Colors.deepPurple),
                underline: Container(
                  height: 2,
                  color: Colors.deepPurpleAccent,
                ),
                onChanged: (String? value) {
                  // This is called when the user selects an item.
                  setState(() {
                    tipo = value!;
                  });
                },
                items: ['0', '1', '2', '3', '4']
                    .map<DropdownMenuItem<String>>((String value) {
                  return DropdownMenuItem<String>(
                    value: value,
                    child: Text(value),
                  );
                }).toList(),
              )
            ],
          ),
          Column(
            children: [Text("Localização"), TextField(controller: locc)],
          ),
          Column(
            children: [
              Text("Url da IMG"),
              TextField(
                controller: imgUrlc,
              )
            ],
          ),
          ElevatedButton(onPressed: _save, child: Text("Guardar"))
        ],
      ),
    );
  }
}
