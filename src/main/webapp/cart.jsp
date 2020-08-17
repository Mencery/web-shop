<%@ page import="com.epam.plekhanov.entity.UserConstants.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${pageContext.response.locale}"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Cart - Flexor Bootstrap Theme</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Facebook Opengraph integration: https://developers.facebook.com/docs/sharing/opengraph -->
    <meta property="og:title" content="">
    <meta property="og:image" content="">
    <meta property="og:url" content="">
    <meta property="og:site_name" content="">
    <meta property="og:description" content="">

    <!-- Twitter Cards integration: https://dev.twitter.com/cards/  -->
    <meta name="twitter:card" content="summary">
    <meta name="twitter:site" content="">
    <meta name="twitter:title" content="">
    <meta name="twitter:description" content="">
    <meta name="twitter:image" content="">

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/icons/114x114.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/icons/72x72.png">
    <link rel="apple-touch-icon-precomposed" href="img/icons/default.png">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900" rel="stylesheet">

    <!-- Bootstrap CSS File -->
    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Libraries CSS Files -->
    <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/owl.theme.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/owl.transitions.min.css" rel="stylesheet">

    <!-- Main Stylesheet File -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/mystyle.css" rel="stylesheet">

    <!--Your custom colour override - predefined colours are: colour-blue.css, colour-green.css, colour-lavander.css, orange is default-->
    <link href="#" id="colour-scheme" rel="stylesheet">

    <link href="css/validation.css" rel="stylesheet">
    <link href="css/colour-green.css" rel="stylesheet">


    <!-- =======================================================
      Theme Name: Flexor
      Theme URL: https://bootstrapmade.com/flexor-free-multipurpose-bootstrap-template/
      Author: BootstrapMade.com
      Author URL: https://bootstrapmade.com
    ======================================================= -->

</head>

<!-- ======== @Region: body ======== -->

<body class="page-index has-hero">

    <!--Change the background class to alter background image, options are: benches, boots, buildings, city, metro -->
    <div id="background-wrapper" class="benches" data-stellar-background-ratio="0.8">

        <div class="header">

            <div class="header-inner">
                <div class="row">
                    <div class="col-md-8">
                        <!--navbar-branding/logo - hidden image tag & site name so things like Facebook to pick up, actual logo set via CSS for flexibility -->
                        <a class="navbar-brand center-block" href="index.html" title="Home">
                            <h1 class="hidden">
                                <img src="img/logo.png" alt="Flexor Logo">
                                Flexor
                            </h1>

                        </a>
                    </div>
                    <div class="col-md-4">
                        <!--user menu-->
                        <ul class="list-inline user-menu pull-right">
                            <ex:login />
                              <ex:language/>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-6 col-sm-offset-3">

            <!-- ======== @Region: #content ======== -->
            <div id="content">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                           <fmt:message key="cart.cart"/>
                        </h3>
                    </div>
                </div>
                <div class="cart">
                    <!-- /row -->
                    <c:choose>
                        <c:when test="${cart==null||cart.products.size()==0}">

                            <div class="panel-heading">
                                <h3 class="panel-title">
                                   <fmt:message key="products.empty"/>
                                </h3>
                            </div>
                        </c:when>

                        <c:otherwise>

                            <table>
                                <tr>
                                    <th><fmt:message key="products.image"/></th>
                                    <th><fmt:message key="products.name"/></th>
                                    <th><fmt:message key="products.manufacturer"/></th>
                                    <th><fmt:message key="products.price"/></th>
                                    <th><fmt:message key="products.amount"/></th>
                                    <th><fmt:message key="products.summary"/></th>
                                </tr>
                                <c:forEach var="product" items="${cart.products}">
                                    <tr data-id="${product.id}">
                                        <td><img src="img/pictures/${product.imgPath}" /></td>


                                        <td>
                                            <c:out value="${product.name}" />
                                        </td>
                                        <td>
                                            <c:out value="${product.manufacturer}" />
                                        </td>
                                        <td class="price" data-id="${product.id}">
                                            <c:out value="${product.price}" />$ * </td>
                                        <td>
                                            <input type="number" class="amount" data-id="${product.id}" min="0"
                                                value="${cart.getAmount(product)}" />
                                        </td>
                                        <td class="summary" data-id="${product.id}"> =
                                            <c:out value="${cart.getPrice(product)}" />$</td>
                                        <td>
                                            <button data-id="${product.id}" class="btn delete"><i
                                                    class="fa fa-close"></i> </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <button class="order-btn left clear"><fmt:message key="products.clear"/></button>
                            <form action="orderload.do">
                                <button type="submit" class="order-btn right order"><fmt:message key="products.order"/></button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>


    </div>
    </div>

    <!-- Required JavaScript Libraries -->
    <script src="lib/jquery/jquery.min.js"></script>
    <script src="lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/stellar/stellar.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/counterup/counterup.min.js"></script>
    <script src="contactform/contactform.js"></script>

    <!-- Template Specisifc Custom Javascript File -->
    <script src="js/custom.js"></script>


    <!--Contact form script -->
    <script src="contactform/contactform.js"></script>

    <script src="js/constants.js"></script>
    <script src="js/cart.js"></script>
</body>

</html>