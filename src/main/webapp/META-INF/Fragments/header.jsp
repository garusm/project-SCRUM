<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header class="page-header">
    <nav class="navbar navbar-expand-lg justify-content-around">
        <a href="/" class="navbar-brand main-logo">
            Zaplanuj <span>Jedzonko</span>
        </a>
        <ul class="nav nounderline text-uppercase">
            <li class="nav-item ml-4">
                <c:choose>
                    <c:when test="${admin.getFirstName()==null}"><a class="nav-link color-header" href="/login">logowanie</a></c:when>
                    <c:otherwise>
                        <a class="nav-link color-header">witaj ${admin.getFirstName()}</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li class="nav-item ml-4">
                <c:choose>
                    <c:when test="${admin.getFirstName()==null}"><a class="nav-link color-header" href="/register">rejestracja</a></c:when>
                    <c:otherwise>
                        <a class="nav-link" href="/logout">wyloguj</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link" href="/about">o aplikacji</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link disabled" href="/app/recipe/list">Przepisy</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link disabled" href="/plan">Zaplanuj Posiłki</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link disabled" href="/contact">Kontakt</a>
            </li>
        </ul>
    </nav>
</header>
