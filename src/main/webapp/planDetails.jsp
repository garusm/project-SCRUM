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

        <div class="m-4 p-3 width-medium ">
            <div class="dashboard-content border-dashed p-3 m-4">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="/app/plan/list" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <div class="schedules-content-header">
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">${plan.name}</p>
                            </div>
                        </div>
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">
                                    ${plan.description}
                                </p>
                            </div>
                        </div>
                    </div>

                    <table class="table">
                        ${dnd.findAll()}
                        ${DayNameDao.findAll()}

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
                                                         class="btn btn-primary rounded-0">Szczegóły</a></td>
                                </tr>
                                </tbody>
                            </c:forEach>
                        </c:forEach>

                        <br><br>

                    </table>

                </div>
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
