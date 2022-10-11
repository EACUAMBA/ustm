<?php
$file = $_FILES['foto'];
$target_path = "./fotos" . "/" . $file['name'];
move_uploaded_file($file['tmp_name'], $target_path);

$date = new \DateTime("now", new DateTimeZone("CAT"));
$hora = $date->format("H");
$diaSemana = $date->format("w");
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?php echo $_POST['nome']; ?></title>

    <link rel="stylesheet" href="./css/style.css">
</head>

<body class="body">
<article class="card">
    <header class="card-header">
      <span class="card-header-text">
        <span>Olá</span>
        <h1><?php echo $_POST['nome']; ?>,</h1>
        <span class="spanGreeting"><?php

            if ($hora > 18) {
                echo "Boa noite!";
            } else if ($hora > 12) {
                echo "Boa tarde!";
            } else if ($hora > 6) {
                echo "Bom dia!";
            } else {
                echo "Boa madrugada!";
            }

            ?></span>
      </span>
    </header>

    <div class="image card-body">
        <img src="<?php echo $target_path; ?>" alt="Um ambiente calmo que representa a minha alma.">
    </div>

    <footer class="footer card-footer">
        <span class="weekDayPrefix"><?php
            if ($diaSemana === 5 || $diaSemana === 6) {
                echo 'Tenha um ótimo';
            } else {
                echo 'Tenha uma ótima';
            }
            ?></php></span><span class="weekDay"><?php

            $dateFormatter = \IntlDateFormatter::create(
                "pt_MZ",
                \IntlDateFormatter::NONE,
                \IntlDateFormatter::NONE,
                \date_default_timezone_get(),
                \IntlDateFormatter::GREGORIAN,
                'EEEE'
            );

            echo $dateFormatter->format($date);
            ?></span>
    </footer>
</article>

<script>

    function getRandomBetween(limiteInferior, limiteSuperior) {
        return Math.floor(Math.random() * (limiteSuperior - limiteInferior) + limiteInferior);
    }

    function changeColor() {
        //Obtendo os elementos;
        const body = document.querySelector("body");

        let colors = Array.of("");
        <?php
        $color_1 = $_POST['color_1'];
        $color_2 = $_POST['color_2'];
        $color_3 = $_POST['color_3'];
        $color_4 = $_POST['color_4'];
        $color_5 = $_POST['color_5'];
        echo "colors = Array.of('". $color_1 ."','". $color_2 ."','". $color_3 ."','". $color_4 . "','". $color_5."')";
        ?>

        //Definindo um intervalo de tempo que será executado uma fração do código.
        setInterval(() => {
            body.style.backgroundColor = colors[getRandomBetween(0, 5)];
        }, 1000);
    }
    changeColor();

    function chooseIMG(){

    }

</script>
</body>

</html>