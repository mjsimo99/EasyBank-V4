<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../includes/header.jsp" %>
<div class="flex justify-center items-center mt-40 mb-40">
    <div class="bg-white p-4 md:p-8 rounded-lg shadow-lg w-full md:w-1/2 lg:w-1/3 text-center">
        <h1 class="text-3xl font-bold mb-4 text-blue-600">Multi-Step Form</h1>

        <form id="stepForm">
            <div class="flex flex-col space-y-4 md:space-x-4 md:flex-row md:space-y-0">
                <div class="col-4 pad-ste active" id="link-back-step-1" onclick="changeStep(1)">
                    <span class="step-number">1</span> Simuler mon crédit
                </div>

                <div class="col-4 pad-ste" id="link-back-step-2" onclick="changeStep(2)">
                    <span class="step-number">2</span> Mes coordonnées
                </div>
                <div class="col-4 pad-ste" id="link-back-step-3" onclick="changeStep(3)">
                    <span class="step-number">3</span> Mes infos personnelles
                </div>
            </div>

            <div id="step1" class="step">
                <div class="bg-white p-4 md:p-8 rounded-lg shadow-lg text-center">
                    <h1 class="text-3xl font-bold mb-5 text-blue-600">Loan Simulation Calculator</h1>

                    <div class="mb-4">
                        <label for="loanAmount" class="block text-gray-700 text-lg font-medium">Loan Amount: <span id="loanAmountValue" class="text-blue-600 text-2xl">1000</span> MAD</label>
                        <input type="range" id="loanAmount" name="loanAmount" min="100" max="300000" step="100" value="1000" class="range-slider">
                    </div>

                    <div class="mb-4">
                        <label for="monthlyPayments" class="block text-gray-700 text-lg font-medium">Number of Monthly Payments: <span id="monthlyPaymentsValue" class="text-blue-600 text-2xl">12</span></label>
                        <input type="range" id="monthlyPayments" name="monthlyPayments" min="1" max="84" step="1" value="12" class="range-slider">
                    </div>

                    <div id="result" class="mt-4 text-2xl text-blue-700">Simulation : 0.00 MAD</div>
                </div>
            </div>

            <div id="step2" class="step">
                <div class="bg-white p-4 md:p-8 rounded-lg shadow-lg text-center">
                    <div class="mb-4">
                        <label for="code" class="block font-semibold text-gray-700 mb-4">Step 2: Mes coordonnées</label>
                        <div class="relative">
                            <input class="input-cal input-base" name="code" id="code" placeholder="" type="text">
                            <label id="label-input">Enter Code Client</label>
                            <button class="bg-green-500 text-white font-semibold py-2 px-4 rounded hover:bg-green-600 mt-5" id="searchClientButton" onclick="checkAndLoadClient()">Search</button>
                        </div>
                    </div>
                </div>
            </div>


            <div id="step3" class="step">
                <div class="bg-white p-4 md:p-8 rounded-lg shadow-lg text-center">
                    <div class="mb-4">
                        <label class="block font-semibold text-gray-700">Step 3: Mes infos personnelles</label>
                        <input class="form-inputs" type="hidden" name="action" value="edit">
                        <label for="nom">Last Name:</label>
                        <input class="form-inputs" type="text" id="nom" name="nom" value="" required><br>
                        <label for="prenom">First Name:</label>
                        <input class="form-inputs" type="text" id="prenom" name=prenom" value="" required><br>
                        <label for="dateN">Date of Birth (YYYY-MM-DD):</label>
                        <input class="form-inputs" type="date" id="dateN" name="dateN" value="" required><br>
                        <label for="dateN">Employe:</label>
                        <select class="form-inputs" id="employeeSelect" name="employeeSelect" required>
                            <option value="">Choose an employee</option>
                            <%--<c:forEach items="${employes}" var="employee">
                                <option value="${employee.matricule}">${employee.nom} ${employee.prenom}</option>
                            </c:forEach>--%>
                        </select>

                        <label for="tel">Phone:</label>
                        <input class="form-inputs" type="text" id="tel" name="tel" value="" required><br>
                        <label for="adress">Address:</label>
                        <input class="form-inputs" type="text" id="adress" name="adress" value="" required><br>
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
