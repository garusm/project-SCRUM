<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <style>
        h3 {

            text-align: center;
        }

        p {

            text-align: center;
        }

        .center {
            margin: auto;
            width: 50%;

            padding-top: 30%;
        }
    </style>
</head>
<body>
<%@include file="/META-INF/Fragments/header.jsp" %>

<section class="dashboard-section"
         style="background-image: url('https://uc.uxpin.com/files/562391/559607/full-bloom-3acdf9.png')"
">
<div class="container pt-4 pb-4">
    <div class="border-dashed view-height">

        <div class="container w-25 center">
            <h3>Lorem ipsum dolor sit amet</h3><br><br>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla turpis eros, sodales eget augue vel,
                facilisis placerat nunc.</p>
            <br>
        </div>
    </div>
</div>
</section>

<%@include file="/META-INF/Fragments/footer.jsp" %>

<%@include file="/META-INF/Fragments/scripts.jsp" %>
</body>
</html>
