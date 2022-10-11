<!doctype html>
<html lang="pt-pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Formulario</title>

    <link rel="stylesheet" href="./css/form.css">
</head>
<body>
<form method="post" enctype="multipart/form-data" action="card-viewer.php">

    <h1>Insira seus dados para mostrar no card</h1>
    <div>
        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome">
    </div>

    <div>
        <label for="foto">Foto</label>
        <input type="file" name="foto" id="foto" alt="Sua foto para o card.">
    </div>

    <div>
        <label for="">Escolha as cores que irão alternar</label>
        <input type="color" name="color_1">
        <input type="color" name="color_2">
        <input type="color" name="color_3">
        <input type="color" name="color_4">
        <input type="color" name="color_5">
    </div>

    <button type="submit">Enviar</button>
</form>
</body>
</html>
