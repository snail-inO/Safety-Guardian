import React from "react";
import { Loader } from "@googlemaps/js-api-loader";
import getCrimeData from "./RetrieveCrimeData";

const loader = new Loader({
  apiKey: "AIzaSyAZGWNClD0GVfVMZAO1SInAkw1nhvILplM",
  libraries: ["visualization"],
});

const mapOptions = {
  center: {
    lat: 43,
    lng: -76,
  },
  zoom: 12,
};

export default class MapElement extends React.Component {
  componentDidMount(): void {
    let map: google.maps.Map | null;

    loader
      .load()
      .then(() => {
        map = new google.maps.Map(
          document.getElementById("map") as HTMLElement,
          mapOptions
        );
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition((position) => {
            map?.setCenter({lat: position.coords.latitude, lng: position.coords.longitude});
            map?.setZoom(15);
          });
        }
      })
      .catch((error: string) => {
        console.log("Google map failed:" + error);
      });

    getCrimeData()
      .then((dataList) => {
        let latLngs: { location: google.maps.LatLng; weight: number }[] = [];

        for (let data of dataList) {
          if (data[0] !== 0 && data[1] !== 0) {
            latLngs.push({
              location: new google.maps.LatLng(data[0], data[1]),
              weight: data[2],
            });
          }
        }
        new google.maps.visualization.HeatmapLayer({
          data: latLngs,
          map: map,
          radius: 50,
        });
      })
      .catch((error) => {
        console.log("Heatmap error: " + error);
      });
  }

  render(): React.ReactNode {
    return <div id="map"></div>;
  }
}
