import 'package:flutter/material.dart';
import 'package:teste1_edilson_alexandre_cuamba_4l7lds1/screens/bilhete_form_screen.dart';

import 'models/bilhete.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      title: 'Bilhetes',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // TRY THIS: Try running your application with "flutter run". You'll see
        // the application has a purple toolbar. Then, without quitting the app,
        // try changing the seedColor in the colorScheme below to Colors.green
        // and then invoke "hot reload" (save your changes or press the "hot
        // reload" button in a Flutter-supported IDE, or press "r" if you used
        // the command line to start the app).
        //
        // Notice that the counter didn't reset back to zero; the application
        // state is not lost during the reload. To reset the state, use hot
        // restart instead.
        //
        // This works for code too, not just values: Most code changes can be
        // tested with just a hot reload.
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<Bilhete>  bilhetes = List.empty(growable: true);

  Bilhete? getBilheteById(List<Bilhete>  bilhetes, int? id){
    var index = bilhetes.indexWhere((element) => element.id == id);
    if(index == -1){
      return null;
    }
    return bilhetes[index];
  }

  void _updateBilhete(Bilhete bi){
    List<Bilhete>  bilhetes = this.bilhetes.toList(growable: true);
    Bilhete? bilhete = getBilheteById(bilhetes, bi.id);
    if(bilhete == null){
      Bilhete bilhete = Bilhete();
      bilhete.id = new DateTime.now().millisecondsSinceEpoch;
      bilhete.preco = bi.preco;
      bilhete.nome = bi.nome;
      bilhete.localizacao = bi.localizacao;
      bilhete.data = bi.data;
      bilhetes.add(bilhete);

    }else {
      bilhete.preco = bi.preco;
      bilhete.nome = bi.nome;
      bilhete.localizacao = bi.localizacao;
      bilhete.data = bi.data;
    }
    setState(() {
      this.bilhetes = bilhetes;
    });
  }

  void _goToBilheteForm() async {
    final result = await Navigator.push(context, MaterialPageRoute(builder: (context) {
      var bilhete = Bilhete();
      return BilheteFormScreen(bilhete: bilhete);
    }));

    if (!context.mounted) return;

    if (result != null) {
      _updateBilhete(result as Bilhete);
    }
  }

  void _goToBilheteFormEdit(Bilhete b) async {
    final result = await Navigator.push(context, MaterialPageRoute(builder: (context) => BilheteFormScreen(bilhete: b)));

    if (!context.mounted) return;

    if (result != null) {
      _updateBilhete(result as Bilhete);
    }
  }

  void _removeBilhete(Bilhete b){
    List<Bilhete>  bilhetes = this.bilhetes.toList(growable: true);
    var index = bilhetes.indexWhere((element) => element.id == b.id);
    bilhetes.removeAt(index);

    setState(() {
      this.bilhetes = bilhetes;
    });
  }

  List<Widget> _renderBilhetes(List<Bilhete> bs){
    List<Widget> ws = List.empty(growable: true);
    for (int i =0 ; i<bs.length; i++){
      Bilhete b = bs[i];
      ws.add(
        InkWell(
            onTap: () => _goToBilheteFormEdit(b),
            child: Column(
              children: [
                Row(mainAxisSize: MainAxisSize.min,
                  children: [
                    Text("id"),
                    Text(": "),
                    Text(b.id.toString())
                  ],),
                Row(mainAxisSize: MainAxisSize.min,
                  children: [
                    Text("nome"),
                    Text(": "),
                    Text(b.nome.toString())
                  ],),
                Row(mainAxisSize: MainAxisSize.min,
                  children: [
                    Text("data"),
                    Text(": "),
                    Text(b.data.toString())
                  ],),
                Row(mainAxisSize: MainAxisSize.min,
                  children: [
                    Text("localizacao"),
                    Text(": "),
                    Text(b.localizacao.toString())
                  ],),
                Row(mainAxisSize: MainAxisSize.min,
                  children: [
                    Text("preco"),
                    Text(": "),
                    Text(b.preco.toString())
                  ],

                ),
                ElevatedButton(onPressed: () => _removeBilhete(b), child: Text("Remover"))
              ],
            )
        )
      );
    }

    return ws;
  }

  @override
  Widget build(BuildContext context) {
    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.
    return Scaffold(
      appBar: AppBar(
        // TRY THIS: Try changing the color here to a specific color (to
        // Colors.amber, perhaps?) and trigger a hot reload to see the AppBar
        // change color while the other colors stay the same.
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text("Bilhetes"),
      ),
      body: Container(
        padding: EdgeInsets.all(10),
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: GridView.count(crossAxisCount: 2, mainAxisSpacing: 10, crossAxisSpacing: 10,
          children: _renderBilhetes(this.bilhetes))
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _goToBilheteForm,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
