import 'package:flutter/material.dart';

import 'models/imovel.dart';
import 'screens/imovel_form_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Imovel',
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
  List<Imovel> imoveis = List.empty(growable: true);

  Imovel? getBilheteById(List<Imovel> bilhetes, int? id) {
    var index = bilhetes.indexWhere((element) => element.id == id);
    if (index == -1) {
      return null;
    }
    return bilhetes[index];
  }

  void _updateImovel(Imovel im) {
    List<Imovel> imoveis = this.imoveis.toList(growable: true);
    Imovel? imovel = getBilheteById(imoveis, im.id);
    if (imovel == null) {
      Imovel imov = Imovel();
      imov.id = new DateTime.now().millisecondsSinceEpoch;
      imov.tipo = im.tipo;
      imov.loc = im.loc;
      imov.imgUrl = im.imgUrl;
      imov.nr = im.nr;
      imoveis.add(imov);
    } else {
      imovel.tipo = im.tipo;
      imovel.loc = im.loc;
      imovel.imgUrl = im.imgUrl;
      imovel.nr = im.nr;
    }
    setState(() {
      this.imoveis = imoveis;
    });
  }

  void _goToBilheteForm() async {
    final result =
        await Navigator.push(context, MaterialPageRoute(builder: (context) {
      var imovel = Imovel();
      imovel.id = new DateTime.now().millisecondsSinceEpoch;
      return ImovelFormScreen(imovel: imovel);
    }));

    if (!context.mounted) return;

    if (result != null) {
      _updateImovel(result as Imovel);
    }
  }

  void _goToImovelFormEdit(Imovel b) async {
    final result = await Navigator.push(context,
        MaterialPageRoute(builder: (context) => ImovelFormScreen(imovel: b)));

    if (!context.mounted) return;

    if (result != null) {
      _updateImovel(result as Imovel);
    }
  }

  void _removeImovel(Imovel b) {
    List<Imovel> imoveis = this.imoveis.toList(growable: true);
    var index = imoveis.indexWhere((element) => element.id == b.id);
    imoveis.removeAt(index);

    setState(() {
      this.imoveis = imoveis;
    });
  }

  Image _getImageOrAlternative(Imovel imovel) {
    try {
      imovel.imgUrl = imovel.imgUrl!.isNotEmpty
          ? imovel.imgUrl
          : "https://picsum.photos/250?=${imovel.id}";
      return Image.network(
          imovel.imgUrl ?? "https://picsum.photos/250?=${imovel.id}");
    } on Exception {
      return Image.network("https://picsum.photos/250?=${imovel.id}");
    }
  }

  List<Imovel> _getType0And1IImovel() {
    return this
        .imoveis
        .where((_element) => _element.tipo == '0' || _element.tipo == '1')
        .toList();
  }

  List<Widget> _renderImoveis(List<Imovel> bs) {
    List<Widget> ws = List.empty(growable: true);
    for (int i = 0; i < bs.length; i++) {
      Imovel b = bs[i];
      ws.add(Container(
        padding: EdgeInsets.all(10),
        child: InkWell(
            onTap: () => _goToImovelFormEdit(b),
            child: Column(
              children: [
                _getImageOrAlternative(b),
                Column(
                  children: [
                    Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [Text("id"), Text(": "), Text(b.id.toString())],
                    ),
                    Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        Text("tipo"),
                        Text(": "),
                        Text(b.tipo.toString())
                      ],
                    ),
                    Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        Text("numero"),
                        Text(": "),
                        Text(b.nr.toString())
                      ],
                    ),
                    Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        Text("localizacao"),
                        Text(": "),
                        Text(b.loc.toString())
                      ],
                    ),
                    ElevatedButton(
                        onPressed: () => _removeImovel(b),
                        child: Text("Remover"))
                  ],
                )
              ],
            )),
      ));
    }

    return ws;
  }

  ListView _buildListView() {
    List<Widget> es = _renderImoveis(this._getType0And1IImovel());

    return ListView.builder(
        itemBuilder: (context, index) => es[index],
        itemCount: es.length,
        scrollDirection: Axis.horizontal);
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
        title: Text("Imovel"),
      ),
      body: SingleChildScrollView(
          child: Column(
        children: [
          Container(
              height: 400,
              width: double.infinity,
              // Center is a layout widget. It takes a single child and positions it
              // in the middle of the parent.
              child: _buildListView()),
          Container(
            child: GridView.count(
                shrinkWrap: true,
                scrollDirection: Axis.vertical,
                childAspectRatio: 0.6,
                crossAxisCount: 2,
                //mainAxisSpacing: 2,
                //crossAxisSpacing: 2,
                children: _renderImoveis(imoveis)),
          )
        ],
      )),
      floatingActionButton: FloatingActionButton(
        onPressed: _goToBilheteForm,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
