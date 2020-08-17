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
  <title>Register - Flexor Bootstrap Theme</title>
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
  <link href="css/validation.css" rel="stylesheet">
  <link href="css/colour-green.css" rel="stylesheet">

  <!--Your custom colour override - predefined colours are: colour-blue.css, colour-green.css, colour-lavander.css, orange is default-->
  <link href="#" id="colour-scheme" rel="stylesheet">

  <!-- =======================================================
    Theme Name: Flexor
    Theme URL: https://bootstrapmade.com/flexor-free-multipurpose-bootstrap-template/
    Author: BootstrapMade.com
    Author URL: https://bootstrapmade.com
    ======================================================= -->
  </head>

  <!-- ======== @Region: body ======== -->

  <body class="fullscreen-centered page-register">
    <!--Change the background class to alter background image, options are: benches, boots, buildings, city, metro -->
    <div id="background-wrapper" class="benches" data-stellar-background-ratio="0.8">

      <!-- ======== @Region: #content ======== -->
      <div id="content">

        <div class="header">
          <div class="header-inner">
            <!--navbar-branding/logo - hidden image tag & site name so things like Facebook to pick up, actual logo set via CSS for flexibility -->
            <a class="navbar-brand center-block" href="index.html" title="Home">
              <h1 class="hidden">
                <img src="img/logo.png" alt="Flexor Logo">
                Flexor
              </h1>
            </a>
              <div class="language" style="top:20%">
                              <ex:language/>
                             </div>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-6 col-sm-offset-3">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h3 class="panel-title">
                 <fmt:message key="register.signup"/>
                </h3>
              </div>
              <div class="panel-body">
                <form accept-charset="UTF-8" id="signin" role="form" method="post" action="register.do">
                  <fieldset>
                    <div class="form-group">
                        <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="fa fa-fw fa-user"></i></span>
                        <input type="text" value = "${cookie['name'].value}"  class="form-control" name="name" data-pattern="[A-Z][a-z]+" placeholder= <fmt:message key="form.name"/> >
                      </div>
                    </div>
                     <p class="text-danger"><c:out value='${requestScope.name}'></c:out></p>
                    <div class="form-group">
                      <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="fa fa-fw fa-user"></i></span>
                        <input type="text"  value = "${cookie['surname'].value}" class="form-control" name="surname"  data-pattern="[A-Z][a-z]+" placeholder=<fmt:message key="form.surname"/> >
                      </div>
                    </div>
                    <p class="text-danger"><c:out value='${requestScope.surname}'></c:out></p>
                    <div class="form-group">
                      <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="fa fa-fw fa-envelope"></i></span>
                        <input type="text"  value = "${cookie['email'].value}" class="form-control" name="email" data-pattern="[a-zA-Z0-9]+@[a-z]+\.[a-z]+"
                        placeholder=<fmt:message key="form.email"/> >
                      </div>
                    </div>
                    <p class="text-danger"><c:out value='${requestScope.email}'></c:out></p>
                    <div class="form-group">
                      <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="fa fa-fw fa-lock"></i></span>
                        <input type="password" value = "${cookie['password'].value}" data-pattern=".{5,}" name="password"  class="form-control" placeholder=<fmt:message key="form.password"/>>
                      </div>
                    </div>
                    <p class="text-danger"><c:out value='${requestScope.password}'></c:out></p>
                    <ex:captcha/>
                    <p class="text-danger"><c:out value='${requestScope.captcha}'></c:out></p>
                    <input name="captcha-number" type="hidden" value="captcha-number">
                   <div class="form-control checkbox">
                    <label>
                      <input  name="mailings"  type="checkbox" value="I want get mailings">
                       <fmt:message key="register.mailings"/>
                    </label>
                  </div>
                  <button class="btn btn-lg btn-primary btn-block" id="sign-in-submit"
                                            type="button">
                      <fmt:message key="register.signmeup"/>
                  </button>
                </fieldset>
              </form>
              <div class="credits">
            <!--
              All the links in the footer should remain intact.
              You can delete the links only if you purchased the pro version.
              Licensing information: https://bootstrapmade.com/license/
              Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Flexor
            -->
            Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- /row -->
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


<!--Contactform script -->
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<script src="contactform/contactform.js"></script>
<script src="js/constants.js"></script>
<script src="js/signin_form_validator.js" ></script>
</body>

</html>
