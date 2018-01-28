<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html lang="pl">
<head>
    <title>Skleppie - fajny sklep internetowy</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="/public/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/public/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" />
    <link rel="stylesheet" type="text/css" href="/public/fontawesome-free-5.0.3/web-fonts-with-css/css/fontawesome-all.min.css" />
    <link rel="stylesheet" type="text/css" href="/public/css/styles.css" />
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Skleppie</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Kategorie <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Telefony</a></li>
                        <li><a href="#">RTV</a></li>
                        <li><a href="#">Gry</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Wszystko</a></li>
                    </ul>
                </li>
                <li><a href="#">O projekcie</a></li>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Szukaj">
                </div>
                <button type="submit" class="btn btn-default">Szukaj</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAnonymous()" >
                    <li><a href="/login"><i class="fas fa-user"></i> Logowanie</a></li>
                    <li><a href="/registration"><i class="fas fa-user-plus"></i> Rejestracja</a></li>
                </sec:authorize>
                <li><a href="/cart"><i class="fas fa-shopping-cart"></i> Koszyk</a></li>
                <sec:authorize access="hasRole('ROLE_CUSTOMER')">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user"></i> ${userName} <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Zamówienia</a></li>
                            <li><a href="#">Moje dane</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/logout">Wyloguj</a></li>
                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Administracja <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/admin/categories">Kategorie</a></li>
                            <li><a href="/admin/products">Produkty</a></li>
                            <li><a href="/admin/pictures">Zdjęcia produktów</a></li>
                            <li><a href="/admin/orders">Zamówienia</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/logout">Wyloguj</a></li>
                        </ul>
                    </li>
                </sec:authorize>

            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>