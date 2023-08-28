//get components
const carsRedLight = document.querySelector('#carsRedLight');
const carsYellowLight = document.querySelector('#carsYellowLight');
const carsGreenLight = document.querySelector('#carsGreenLight');
const turnRightGreenLight = document.querySelector('#turnRightGreenLight');
const turnRightYellowLight = document.querySelector('#turnRightYellowLight');
const manStandingLight = document.querySelector('#manStandingLight');
const manWalkingLight = document.querySelector('#manWalkingLight');

function enableTurnRightGreenLight(){
    setTimeout(() => {
        carsRedLight
            .classList
            .remove('deactivated');

        turnRightGreenLight
            .classList
            .remove('deactivated-svg');

        manStandingLight
            .classList
            .remove("deactivated-svg");

        enableTurnRightYellowLight();
    }, 0);
}

function enableTurnRightYellowLight(){
    setTimeout(() => {
        turnRightGreenLight.classList.add('deactivated-svg');
        turnRightYellowLight.classList.remove('deactivated-svg');

        enableCarsGreenLight();
    }, 5000);
}

function enableCarsGreenLight(){
    setTimeout(() => {
        carsRedLight
            .classList
            .add('deactivated');

        turnRightYellowLight
            .classList
            .add('deactivated-svg');

        carsGreenLight
            .classList
            .remove('deactivated')

        enableCarsYellowLight();
    }, 3000)
}

function enableCarsYellowLight(){
    setTimeout(() => {
        carsGreenLight
            .classList
            .add('deactivated')

        carsYellowLight
            .classList
            .remove('deactivated');

        enableCarsRedLight();
    }, 10000)
}

function enableCarsRedLight(){
    setTimeout(() => {
        carsYellowLight
            .classList
            .add('deactivated')

        carsRedLight
            .classList
            .remove('deactivated');

        manStandingLight
            .classList
            .add("deactivated-svg");

        manWalkingLight
            .classList
            .remove("deactivated-svg");
        deactivateCarsRedLightAndStartTurnRightGreenLightAgain();
    }, 5000)
}

function deactivateCarsRedLightAndStartTurnRightGreenLightAgain(){
    setTimeout(() => {
        carsRedLight
            .classList
            .add('deactivated')

        manWalkingLight
            .classList
            .add("deactivated-svg");

        manStandingLight
            .classList
            .remove("deactivated-svg");

        enableTurnRightGreenLight();
    }, 10000)
}

enableTurnRightGreenLight();