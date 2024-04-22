import 'package:flutter/material.dart';
import 'package:teste1_edilson_alexandre_cuamba_4l7lds1/models/bilhete.dart';

class BilheteFormScreen extends StatefulWidget{

  final Bilhete bilhete;

  const BilheteFormScreen({super.key, required this.bilhete});

  @override
  State<StatefulWidget> createState() {
    return BilheteFormState(bilhete);
  }
}

class BilheteFormState extends State<BilheteFormScreen>{
  BilheteFormState(this.bilhete);
  final Bilhete bilhete;





  @override
  Widget build(BuildContext context) {

    var idc = TextEditingController(text: "${bilhete.id}");
    var nomec = TextEditingController(text: bilhete.nome);
    var datac = TextEditingController(text: bilhete.data);
    var locc = TextEditingController(text: bilhete.localizacao);
    var precoc = TextEditingController(text: bilhete.preco);



    void _save(){
      double v;
      try{
        v = double.parse(precoc.text);
      } on Exception{
        v = 0;
      }

      if(v < 150){
        var alert = const AlertDialog(title: Text("Valor muito baixo"),
          icon: Icon(Icons.warning),
        );

        showDialog(context: context, builder: (context) => alert,);
        return;
      }

      this.bilhete.nome = nomec.value.text;
      this.bilhete.data = datac.value.text;
      this.bilhete.localizacao = locc.value.text;
      this.bilhete.preco = precoc.value.text;

      Navigator.pop(context, this.bilhete);
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
              TextField(controller: idc, enabled: false,)
            ],
          ),
          Column(
            children: [
              Text("Nome"),
              TextField(controller: nomec,)
            ],
          ),
          Column(
            children: [
              Text("Data"),
              TextField(controller: datac, keyboardType: TextInputType.datetime)
            ],
          )
          ,Column(
            children: [
              Text("Localização"),
              TextField(controller: locc,)
            ],
          ),
          Column(
            children: [
              Text("Preco"),
              TextField(controller: precoc, keyboardType: TextInputType.number,)
            ],
          ),
          ElevatedButton(onPressed: _save, child: Text("Guardar"))
        ],
      ),
    );
  }

}
