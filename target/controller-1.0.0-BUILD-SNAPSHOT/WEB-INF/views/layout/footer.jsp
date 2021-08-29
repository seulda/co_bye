<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  </div> <!-- .wrapper -->
  
  <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
  <script src='${pageContext.request.contextPath}/resources/js/jquery.stickOnScroll.js'></script>
  <script src="${pageContext.request.contextPath}/resources/js/tinycolor-min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/config.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/Chart.min.js"></script>
  
  <script>
    /* defind global options */
    Chart.defaults.global.defaultFontFamily = base.defaultFontFamily;
    Chart.defaults.global.defaultFontColor = colors.mutedColor;
  </script>
  <script src="${pageContext.request.contextPath}/resources/js/apexcharts.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/apps.js"></script>
  
  <!-- Global site tag (gtag.js) - Google Analytics -->
  <script async src="https://www.googletagmanager.com/gtag/js?id=UA-56159088-1"></script>
  <script>
    window.dataLayer = window.dataLayer || [];
    function gtag() {
      dataLayer.push(arguments);
    }
    gtag('js', new Date());
    gtag('config', 'UA-56159088-1');
  </script>


  </body>

  </html>