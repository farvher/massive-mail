/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function initLocation() {
    var geo = "qtf_geosearch:center";
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
                latitudMovil: position.coords.latitude,
                longitudMovil: position.coords.longitude
            };
            $('<input>').attr({
                type: 'hidden',
                name: geo,
                value: "(" + pos.latitudMovil + "," + pos.longitudMovil + ")"
            }).appendTo('#form-search-home');
            $('#home-desktop-location').attr("placeholder", "Cerca a mi ubicación");

        }, function (failure) {
            jQuery.post("https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyDCa1LUe1vOczX1hO_iGYgyo8p_jYuGOPU", function (success) {
                 var pos = {
                latitudMovil: success.location.lat,
                longitudMovil: success.location.lng
            };
            $('<input>').attr({
                type: 'hidden',
                name: geo,
                value: "(" + pos.latitudMovil + "," + pos.longitudMovil + ")"
            }).appendTo('#form-search-home');
            $('#home-desktop-location').attr("placeholder", "Cerca a mi ubicación");
            }).fail(function (err) {
               alert("API Geolocation error! \n\n" + err);
                 });
        }
        );
    } else {
        alert('Browser does not support geolocation');
    }
}


