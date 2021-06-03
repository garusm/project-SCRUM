<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="/META-INF/Fragments/head.jsp" %>
</head>

<body>
<header class="page-header">
    <%@include file="/META-INF/Fragments/header.jsp" %>
</header>

<section class="dashboard-section"
         style="background-image: url('https://uc.uxpin.com/files/562391/559607/full-bloom-3acdf9.png')">
    <div class="row dashboard-nowrap">

        <%@include file="/META-INF/Fragments/appSideBanner.jsp" %>

        <div class="m-4 p-3 width-medium">

            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <form action="/app/recipe/plan/add" method="post">
                    <div class="row border-bottom border-3 p-1 m-1">
                        <div class="col noPadding">
                            <h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU</h3>

                            <div class="col d-flex justify-content-end mb-2 noPadding">
                                <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="schedules-content">

                        <div class="form-group row">
                            <label for="choosePlan" class="col-sm-2 label-size col-form-label">
                                Wybierz plan
                            </label>
                            <div class="col-sm-3">
                                <select class="form-control" id="choosePlan" name="choosePla">
                                    <c:forEach items="${choosePlan}" var="plan">
                                        <option value="${plan.id}">${plan.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="name" class="col-sm-2 label-size col-form-label">
                                Nazwa posiłku
                            </label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" value="" id="name" name="name"
                                       placeholder="Nazwa posiłku">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="number" class="col-sm-2 label-size col-form-label">
                                Numer posiłku
                            </label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" value="" id="number" name="number"
                                       placeholder="Numer posiłki">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="recipie" class="col-sm-2 label-size col-form-label">
                                Przepis
                            </label>
                            <div class="col-sm-4">
                                <select class="form-control" id="recipie" name="recipie">
                                    <c:forEach items="${recipe}" var="list">
                                        <option value="${list.id}">${list.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="day" class="col-sm-2 label-size col-form-label">
                                Dzień
                            </label>
                            <div class="col-sm-2">
                                <select class="form-control" id="day" name="day">
                                    <option value="1">poniedziałek</option>
                                    <option value="2">wtorek</option>
                                    <option value="3">środa</option>
                                    <option value="4">czwartek</option>
                                    <option value="5">piątek</option>
                                    <option value="6">sobota</option>
                                    <option value="7">niedziela</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</section>

<%@include file="/META-INF/Fragments/scripts.jsp" %>
</body>
</html>