
const currentDate = new Date();
const currentHour = currentDate.getHours()


function chooseGreeting(currentHour) {
  console.log(`Current hour is ${currentHour}`)
  if (currentHour >= 18)
    return 'Boa noite!';

  if (currentHour >= 12)
    return 'Boa tarde!';

  if (currentHour >= 6)
    return 'Bom dia!';
  return 'Boa madrugada!';
}
function getRandomBetween(limiteInferior, limiteSuperior) {
  return Math.floor(Math.random() * (limiteSuperior - limiteInferior) + limiteInferior);
}

function greet(currentHour) {
  ////Obtendo os elementos;
  const spanGreeting = document.querySelector('.spanGreeting');

  //Chamando a função que escolhe o cumprimento certo e passando como argumento a hora corrente;
  let greeting = chooseGreeting(currentHour);

  //Definindo o cumprimento no elemento HTML;
  spanGreeting.innerText = greeting;
}
//greet(currentHour);

function chooseWeekDay(currentDate) {
  //Obtendo os elementos;
  const spanWeekDay = document.querySelector('.weekDay');
  const spanWeekDayPrefix = document.querySelector('.weekDayPrefix');

  //Obtendo os valores do dia da semana e o numero do dia;
  const weekDay = currentDate.toLocaleDateString('pt', { weekday: 'long' });
  const weekDayNumber = currentDate.getDay();
  console.log(`Current week day is ${weekDay}, week day number is ${weekDayNumber}`);

  //Determinando o prefixo, se é "um ótimo" ou "uma ótima";
  if (weekDayNumber === 5 || weekDayNumber === 6) {
    spanWeekDayPrefix.innerText = 'Tenha um ótimo';
  } else {
    spanWeekDayPrefix.innerText = 'Tenha uma ótima';
  }

  //Definindo o dia da semana.
  spanWeekDay.innerText = weekDay;
}
//chooseWeekDay(currentDate);

function changeColor() {
  //Obtendo os elementos;
  const body = document.querySelector("body");

  //Definindo um intervalo de tempo que será executado uma fração do código.
  setInterval(() => {
    let r = getRandomBetween(100, 255);
    let g = getRandomBetween(100, 150);
    let b = getRandomBetween(100, 255);
    console.log(`Current color is ${r}, ${g}, ${b}`)
    body.style.backgroundColor = `rgb(${r}, ${g}, ${b})`;
  }, 1000);
}
//changeColor();




