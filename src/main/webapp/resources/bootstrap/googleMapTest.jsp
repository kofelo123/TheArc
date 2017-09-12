<!DOCTYPE html>
<html>
  <head>
    <style>
      #map {
        height: 400px;
        width: 100%;
       }
    </style>
  </head>
  <body>
    <h3>My Google Maps Demo</h3>
    <div id="map"></div>
    <script>
      function initMap() {
        var uluru = {lat: 35.838753, lng: 128.468297};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 13,
          center: uluru
        });
        var marker = new google.maps.Marker({
          position: uluru,
          map: map
        });
      }
    </script>
    <script async defer 
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCSnMW4kIIlD48ao8_Rf7pm9qs51WeCyIk&callback=initMap">
    </script>
  </body>
</html>