<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edilson o PHP</title>
</head>

<body>

  <form action="./">
    <label for="nome">Nome:</label>
    <input type="text" name="nome" id="nome" value="Edilson">
    <button type="submit">Submit</button>
  </form>
  <?php 
  print_r($_SESSION);
  $nome = '';
  echo "Eu sou o $nome";
  phpinfo();
  ?>
</body>

</html>