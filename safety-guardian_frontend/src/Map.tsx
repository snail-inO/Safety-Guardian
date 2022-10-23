import React from "react";
import { Loader } from "@googlemaps/js-api-loader";
import getCrimeData from "./RetrieveCrimeData";

const loader = new Loader({
  apiKey: "AIzaSyAZGWNClD0GVfVMZAO1SInAkw1nhvILplM",
});

const mapOptions = {
  center: {
    lat: 43.048,
    lng: -76.13489,
  },
  zoom: 15,
};

export default function MapElement(): JSX.Element {
  loader
    .load()
    .then((google) => {
      new google.maps.Map(
        document.getElementById("map") as HTMLElement,
        mapOptions
      );
    })
    .catch((e) => {
      console.log("Google map failed");
    });
    getCrimeData();

  return <div id="map"></div>;
}
