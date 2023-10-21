var currentStep = 1;
showStep(currentStep);

function showStep(step) {
    var steps = document.getElementsByClassName("step");
    if (step < 1) currentStep = 1;
    if (step > steps.length) currentStep = steps.length;
    for (var i = 0; i < steps.length; i++) {
        steps[i].style.display = "none";
    }
    steps[currentStep - 1].style.display = "block";

    var stepButtons = document.getElementsByClassName("col-4 pad-ste");
    for (var i = 0; i < stepButtons.length; i++) {
        stepButtons[i].classList.remove("active");
    }

    var currentStepButton = document.getElementById("link-back-step-" + currentStep);
    currentStepButton.classList.add("active");

    if (currentStep === 1) {
        document.getElementById("prevBtn").style.display = "none";
    } else if (currentStep === steps.length) {
        document.getElementById("prevBtn").style.display = "block";
        document.getElementById("nextBtn").innerHTML = "Submit";
    } else {
        document.getElementById("prevBtn").style.display = "block";
        document.getElementById("nextBtn").innerHTML = "Next";
    }
}

function nextStep() {
    var steps = document.getElementsByClassName("step");
    if (currentStep < steps.length) {
        currentStep++;
        showStep(currentStep);
    }
    if (currentStep === 2) {
        saveToLocalStorage();
    }
}

function prevStep() {
    if (currentStep > 1) {
        currentStep--;
        showStep(currentStep);
    }
}

function changeStep(step) {
    showStep(step);
}

function saveToLocalStorage() {
    var loanAmountInput = document.getElementById("loanAmount");
    var monthlyPaymentsInput = document.getElementById("monthlyPayments");
    var simulation = document.getElementById("result");

    localStorage.setItem("LoanAmount", loanAmountInput.value);
    localStorage.setItem("MonthlyPayments", monthlyPaymentsInput.value);
    localStorage.setItem("Simulation", simulation);
}