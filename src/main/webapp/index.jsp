<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="product.do"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${pageContext.response.locale}"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Flexor Bootstrap Theme</title>
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
  <link href="css/colour-green.css" rel="stylesheet">
  <!--Your custom colour override - predefined colours are: colour-blue.css, colour-green.css, colour-lavander.css, orange is default-->
  <link href="#" id="colour-scheme" rel="stylesheet">

  <!-- Bootstrap -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  
  <!-- =======================================================
    Theme Name: Flexor
    Theme URL: https://bootstrapmade.com/flexor-free-multipurpose-bootstrap-template/
    Author: BootstrapMade.com
    Author URL: https://bootstrapmade.com
    ======================================================= -->
  </head>
  <body class="page-index has-hero">
    <!--Change the background class to alter background image, options are: benches, boots, buildings, city, metro -->
    <div id="background-wrapper" class="buildings" data-stellar-background-ratio="0.1" style="background-position: 0px -10.8px;">

      <!-- ======== @Region: #navigation ======== -->
      <div id="navigation" class="wrapper">

        <!--Header & navbar-branding region-->
        <div class="header">
          <div class="header-inner container">
            <div class="row">
              <div class="col-md-8">
                <!--navbar-branding/logo - hidden image tag & site name so things like Facebook to pick up, actual logo set via CSS for flexibility -->
                <a class="navbar-brand" href="#" title="Home">
                  <h1 class="hidden">
                    <img src="img/logo.png" alt="Flexor Logo">
                    Flexor
                  </h1>
                </a>
                <div class="navbar-slogan">
                  Responsive HTML Theme
                  <br> By BootstrapMade.com
                </div>
              </div>
              <!--header rightside-->
              <div class="col-md-4">
                <!--user menu-->
                <ul class="list-inline user-menu pull-right">
                  <ex:login/>
                  <ex:language/>
                </ul>

              </div>
            </div>
          </div>
        </div>
        <div class="container">
          <div class="navbar navbar-default">
            <!--mobile collapse menu button-->
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse" aria-expanded="false"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
            <!--social media icons-->
            
            <!--everything within this div is collapsed on mobile-->
            <div class="navbar-collapse collapse">
              <ul class="nav my-navbar-nav" id="main-menu">
                <li class="icon-link">
                  <a href=""><i class="fa fa-home"></i></a>
                </li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><fmt:message key="index.menu.size"/></a>
                  <!-- Dropdown Menu -->
                  <ul class="dropdown-menu">
                    <form class = "form-group" action ="productgrid.do">
                      <li>Product on page:<br> <input type='number' name='productOnPage' min = '1' max = "${fn:length(products)}"   />
                        <button class="p-add" type ="submit"><fmt:message key="apply"/></button></li>
                      </form>
                    </ul>
                  </li>

                </ul>
              </div>
              <!--/.navbar-collapse -->
            </div>
          </div>
        </div>
      </div>

      <!-- Modal -->
      <div class="modal" id="exampleModal"  role="dialog" >
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h2 class="modal-title" id="exampleModalLabel"><fmt:message key="index.filter.filter"/></h2>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <form action="productfilter.do">

               <div class="form-group form-group-lg">
                 <div class = "p-name"><fmt:message key="index.filter.name"/></div>
                 <c:forEach var="name" begin ="0" items="${names}" >
                 <div class="form-check-inline">
                   <input type="checkbox" name="name" class="form-check-input" value="${name}"> <div class="p-choose"><c:out value="${name}"></c:out></div>
                 </div>
               </c:forEach>
             </div>
             
             <div class="form-group form-group-lg">
              <div class = "p-name"><fmt:message key="index.filter.manufacturer"/></div>
              <c:forEach var="manufacturer" items="${manufacturers}">
              <div class="form-check-inline">
                <input type="checkbox" name ="manufacturer" class="form-check-input" value="${manufacturer}"> <div class = "p-choose" ><c:out value="${manufacturer}"></c:out></div>
              </div>
            </c:forEach>
          </div>
          <div class="form-group form-group-lg">
            <div class = "p-name"><fmt:message key="index.filter.category"/></div>
            <c:forEach var="category" items="${categories}">
            <div class="form-check-inline">
              <input type="checkbox" name="category" class="form-check-input" value="${category}"> <div class = "p-choose" ><c:out value="${category}"></c:out></div>
            </div>
          </c:forEach>
        </div>


        <div class="form-group form-group-lg">
         <div class="row">
          <div class="col-sm-9">
            <div class = "p-name" ><fmt:message key="index.filter.price"/> <fmt:message key="index.filter.from"/> <c:out value="${minprice}"></c:out> <fmt:message key="index.filter.to"/> <c:out value="${maxprice}"></c:out> </div>
            <br>
            <div class = "p-name"><fmt:message key="index.filter.from"/></div>
            <input type="number" class="form-control" id="from" name="inputmin" max ="${maxprice}" min="${minprice}"/>
          </div>
          <div class="col-sm-9">
            <div class = "p-name"><fmt:message key="index.filter.to"/></div>
            <input type="number" class="form-control" id="to" name="inputmax" max ="${maxprice}"  min="${minprice}"/>
          </div>
        </div>
      </div>
      <button class="p-add" id="log-in-submit" type="submit">
       <fmt:message key="apply"/>
     </button>
   </form>

 </div>
 <div class="modal-footer">
  <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="close"/></button>
</div>
</div>
</div>
</div>

<!-- ========@Filter====================== -->
<div class="modal" id="sort"  role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="modal-title" id="exampleModalLabel"><fml:massage key="index.sort.sort"/></h2>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="filterproduct.do">

         <div class="form-group form-group-lg">
           <div class = "p-name"><fmt:message key="index.filter.name"/></div>

         </div>
         <div class="form-group form-group-lg">
           <div class = "p-name"><fmt:message key="index.filter.price"/></div>

           
         </div>
        <button class="p-add" id="log-in-submit" type="submit">
         <fmt:message key="apply"/>
       </button>
     </form>

   </div>
   <div class="modal-footer">
    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="close"/></button>
  </div>
</div>
</div>
</div>
<!-- ======== @Region: #content ======== -->

<div id="content">
<form action="cart.do">
<button id="cart" type="submit">
<img alt=" " class="cart-img" src = "img/cart/cart.png">
<div id="cart-products">0</div>
</button>
</form>
  <!-- Mission Statement -->

  <!--Showcase-->

  <div class="pagination-container" >
   <ex:products/>

 </div>
</div>
<div class="owl-controls clickable" style="display: none;"><div class="owl-buttons"><div class="owl-prev">prev</div><div class="owl-next">next</div></div></div></div>
<!-- /content -->
<!-- Call out block -->

<!-- ======== @Region: #footer ======== -->
<footer id="footer"
class="block block-bg-grey-dark block-bg-img"
data-block-bg-img="img/bg_footer-map.png" data-stellar-background-ratio="0.4" style="background-image: url(&quot;img/bg_footer-map.png&quot;); background-position: 0px 614.254px;">
<div class="container">

  <div class="row subfooter">
    <!--@todo: replace with company copyright details-->
    <div class="col-md-7">
      <p>Copyright © Flexor Theme</p>
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
        <div class="col-md-5">
          <ul class="list-inline pull-right">
            <li><a href="#">Terms</a></li>
            <li><a href="#">Privacy</a></li>
            <li><a href="#">Contact Us</a></li>
          </ul>
        </div>
      </div>
      <a href="#top" class="scrolltop" style="display: none;">Top</a>
    </div>
  </footer>
  <!-- Required JavaScript Libraries -->
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.min.js"></script>
  <script src="lib/owlcarousel/owl.carousel.min.js"></script>
  <script src="lib/stellar/stellar.min.js"></script>
  <script src="lib/waypoints/waypoints.min.js"></script>
  <script src="lib/counterup/counterup.min.js"></script>
  <script src="contactform/contactform.js"></script>
  <!-- Template Specisifc Custom Javascript File -->
  <script src="js/constants.js"></script>
  <script src="js/custom.js"></script>
  <script src="js/cart.js"></script>
  <script src="js/pagination.js"></script>
  <!--Contactform script -->
  <script src="contactform/contactform.js"></script>
</body>

</html>
