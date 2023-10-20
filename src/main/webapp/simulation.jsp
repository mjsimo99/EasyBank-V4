<%@ include file="../includes/header.jsp" %>
<div class="flex justify-center items-center mt-20 mb-20">
    <div class="bg-white p-4 md:p-8 rounded-lg shadow-lg w-full md:w-1/2 lg:w-1/3 text-center">
        <h1 class="text-3xl font-bold mb-4 text-blue-600">Multi-Step Form</h1>

        <form id="stepForm">
            <div class="flex flex-col space-y-4 md:space-x-4 md:flex-row md:space-y-0">
                <div class="col-4 pad-ste active" id="link-back-step-1" onclick="changeStep(1)">
                    <span class="step-number">1</span> Simuler mon credit
                </div>

                <div class="col-4 pad-ste" id="link-back-step-2" onclick="changeStep(2)">
                    <span class="step-number">2</span> Mes coordonnees
                </div>
                <div class="col-4 pad-ste" id="link-back-step-3" onclick="changeStep(3)">
                    <span class="step-number">3</span> Mes infos personnelles
                </div>
            </div>

            <div id="step1" class="step">
                <div class="bg-white p-4 md:p-8 rounded-lg shadow-lg text-center">
                    <h1 class="text-3xl font-bold mb-5 text-blue-600">Loan Simulation Calculator</h1>

                    <div class="mb-4">
                        <label for="loanAmount" class="block text-gray-700 text-lg font-medium">Enter Borrowed Capital (Loan Amount): <span id="loanAmountValue" class="text-blue-600 text-2xl">1000</span> MAD</label>
                        <input type="range" id="loanAmount" name="loanAmount" min="100" max="300000" step="100" value="1000" class="range-slider">
                    </div>

                    <div class="mb-4">
                        <label for="monthlyPayments" class="block text-gray-700 text-lg font-medium">Number of Monthly Payments: <span id="monthlyPaymentsValue" class="text-blue-600 text-2xl">12</span></label>
                        <input type="range" id="monthlyPayments" name="monthlyPayments" min="1" max="84" step="1" value="12" class="range-slider">
                    </div>

                    <div id="result" class="mt-4 text-2xl text-blue-700">Calculated Monthly Payment: 0.00 MAD</div>
                </div>
            </div>

            <div id="step2" class="step">
                <div class="bg-white p-4 md:p-8 rounded-lg shadow-lg text-center">
                    <div class="mb-4">
                        <label for="code" class="block font-semibold text-gray-700 mb-4">Step 2: Mes coordonnées</label>
                        <div class="relative">
                            <input class="input-cal input-base" name="code" id="code" placeholder="" type="text">
                            <label id="label-input">Enter Code Client</label>
                        </div>
                    </div>
                </div>
            </div>

            <div id="step3" class="step">
                <div class="bg-white p-4 md:p-8 rounded-lg shadow-lg text-center">
                    <div class="mb-4">
                        <label class="block font-semibold text-gray-700">Step 3: Mes infos personnelles</label>
                        <input class="form-inputs" type="hidden" name="action" value="edit">
                        <input class="form-inputs" type="hidden" id="code" name="code" value="${client.code}" readonly><br>
                        <label for="nom">Last Name:</label>
                        <input class="form-inputs" type="text" id="nom" name="nom" value="${client.nom}" required><br>
                        <label for="prenom">First Name:</label>
                        <input class="form-inputs" type="text" id="prenom" name="prenom" value="${client.prenom}" required><br>
                        <label for="dateN">Date of Birth (YYYY-MM-DD):</label>
                        <input class="form-inputs" type="date" id="dateN" name="dateN" value="${client.dateN}" required><br>
                        <label for="tel">Phone:</label>
                        <input class="form-inputs" type="text" id="tel" name="tel" value="${client.tel}" required><br>
                        <label for="adress">Address:</label>
                        <input class="form-inputs" type="text" id="adress" name="adress" value="${client.adress}" required><br>
                    </div>
                </div>
            </div>

            <div class="mt-4 flex justify-center">
                <button type="button" id="prevBtn" class="btn button-update mx-4" onclick="prevStep()">Previous</button>
                <button type="button" id="nextBtn" class="btn button-update" onclick="nextStep()">Next</button>
            </div>
        </form>
    </div>
</div>
<%@ include file="../includes/footer.jsp" %>

<script>
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

        // Clear the color for all step buttons
        var stepButtons = document.getElementsByClassName("col-4 pad-ste");
        for (var i = 0; i < stepButtons.length; i++) {
            stepButtons[i].classList.remove("active");
        }

        // Set the color for the current step button
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
    }

    function prevStep() {
        if (currentStep > 1) {
            currentStep--;
            showStep(currentStep);
        }
    }
</script>

<script>
    function updateValues() {
        var loanAmountInput = document.getElementById("loanAmount");
        var loanAmountValue = document.getElementById("loanAmountValue");
        loanAmountValue.innerHTML = loanAmountInput.value;

        var monthlyPaymentsInput = document.getElementById("monthlyPayments");
        var monthlyPaymentsValue = document.getElementById("monthlyPaymentsValue");
        monthlyPaymentsValue.innerHTML = monthlyPaymentsInput.value;
    }

    function calculateMonthlyPayment() {
        var borrowedCapital = parseFloat(document.getElementById("loanAmount").value);
        var numberOfMonthlyPayments = parseInt(document.getElementById("monthlyPayments").value);

        var annualInterestRate = 0.12;
        var monthlyInterestRate = annualInterestRate / 12;

        var monthlyPayment = (borrowedCapital * monthlyInterestRate) /
            (1 - Math.pow(1 + monthlyInterestRate, -numberOfMonthlyPayments));

        document.getElementById("result").innerHTML = "Calculated Monthly Payment: " + monthlyPayment.toFixed(2) + " MAD";
    }

    document.getElementById("loanAmount").addEventListener("input", updateValues);
    document.getElementById("monthlyPayments").addEventListener("input", updateValues);
    document.getElementById("loanAmount").addEventListener("input", calculateMonthlyPayment);
    document.getElementById("monthlyPayments").addEventListener("input", calculateMonthlyPayment);

    updateValues();
    calculateMonthlyPayment();
</script>
