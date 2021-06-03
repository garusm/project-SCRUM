<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@include file="/META-INF/Fragments/head.jsp" %>
</head>
<body>
<%@include file="/META-INF/Fragments/header.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="/META-INF/Fragments/appSideBanner.jsp" %>

        <div class="m-4 p-4 width-medium">
            <div class="dashboard-header m-4">
                <div class="dashboard-menu">
                    <div class="menu-item border-dashed">
                        <a href="/app/recipe/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="/app/plan/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj plan</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis do planu</span>
                        </a>
                    </div>
                </div>

                <div class="dashboard-alerts">
                    <div class="alert-item alert-info">
                        <i class="fas icon-circle fa-info-circle"></i>
                        <span class="font-weight-bold">Liczba przepisów: ${quantityOfUsersRecipes}</span>
                    </div>
                    <div class="alert-item alert-light">
                        <i class="far icon-calendar fa-calendar-alt"></i>
                        <span class="font-weight-bold">Liczba planów: ${quantityOfUsersPlans}</span>

                    </div>
                </div>
            </div>
            <div class="m-4 p-4 border-dashed">
                <h2 class="dashboard-content-title">
                    <span>Ostatnio dodany plan:</span> ${lastPlan.getName()}
                </h2>
                <table class="table">
                    <c:forEach var="entry" items="${displayList}">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">${entry.key}</th>
                            <th class="col-8"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <c:forEach var="info" items="${entry.value}">
                            <tbody>
                            <tr class="d-flex">
                                <td class="col-2">${info.getMealName()}</td>
                                <td class="col-8">${info.getRecipeName()}</td>
                                <td class="col-2"><a href="/app/recipe/details?id=${info.getRecipeId()}"
                                                     class="btn btn-primary rounded-0">Szczegóły</a>

                            </tr>
                            </tbody>
                        </c:forEach>
                    </c:forEach>

                    <br><br>

                </table>

            </div>
        </div>
    </div>
</section>

<%@include file="/META-INF/Fragments/footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>
