import React, { MouseEventHandler } from "react";
import { Loader } from "@googlemaps/js-api-loader";
import getCrimeData from "./RetrieveCrimeData";
import { calculateRoute, Route } from "./CalculateRoute";
import './Map.css';

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
const handleClick: MouseEventHandler = () => {
  removeMarkers();
};

type RiskProps = {
  risk: number;
};

type MapState = {
  risk: number;
};

let map: google.maps.Map | null = null;
let markers: google.maps.Marker[] = [];
let from: Array<number>;
let to: Array<number>;
let directionsRenderer: google.maps.DirectionsRenderer;

export default class MapElement extends React.Component<Object, MapState> {
  constructor(props: Object) {
    super(props);
    this.state = { risk: -1 };
    this.loadMap = this.loadMap.bind(this);
    this.placeMarkers = this.placeMarkers.bind(this);
  }

  loadMap() {
    loader
      .load()
      .then(() => {
        map = new google.maps.Map(
          document.getElementById("map") as HTMLElement,
          mapOptions
        );

        map.addListener("click", this.placeMarkers);
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition((position) => {
            map?.setCenter({
              lat: position.coords.latitude,
              lng: position.coords.longitude,
            });
            map?.setZoom(15);
          });
        }
      })
      .catch((error: string) => {
        console.log("Google map failed:" + error);
      });
  }

  placeMarkers(event: google.maps.MapMouseEvent) {
    if (markers.length === 2) {
      return;
    }

    markers.push(
      new google.maps.Marker({
        position: event.latLng,
        map: map,
      })
    );
    if (markers.length === 1) {
      this.setState({risk: -1})
      from = [event.latLng?.lat() as number, event.latLng?.lng() as number];
    } else if (markers.length === 2) {
      to = [event.latLng?.lat() as number, event.latLng?.lng() as number];
      this.setState({ risk: 0 });
      calculateRoute(from, to)
        .then((res) => {
          this.setState({ risk: res.avgRisk as number });
          // drawpath(res);
          calculateDirection(res);
        })
        .catch(console.log);
    }
  }
  componentDidMount(): void {
    this.loadMap();
    loadCrimeData();
  }

  render(): React.ReactNode {
    return (
      <>
        <div id="map"></div>
        <RiskElement risk={this.state.risk} /> <ClearMarkers />
      </>
    );
  }
}

function ClearMarkers(): JSX.Element {
  return <button onClick={handleClick}>Clear route plan</button>;
}

function RiskElement(props: RiskProps): JSX.Element {
  if (props.risk > 0) {
    return <p>Average Risk: {props.risk}</p>;
  } else if (props.risk === 0) {
    return <p>Calculating ...</p>;
  } else {
    return <p></p>;
  }
}

function removeMarkers() {
  for (let marker of markers) {
    marker.setMap(null);
  }
  directionsRenderer.setMap(null);
  markers = [];
}

function drawpath(route: Route) {
  new google.maps.Polyline({
    path: arrToObj(route.waypoints),
    geodesic: true,
    map: map,
  });
}

function calculateDirection(route: Route) {
  let directionsService = new google.maps.DirectionsService();
  directionsRenderer = new google.maps.DirectionsRenderer();
  directionsRenderer.setMap(map);

  directionsService
    .route({
      origin: new google.maps.LatLng(from[0], from[1]),
      destination: new google.maps.LatLng(to[0], to[1]),
      waypoints: arrToWaypoint(route.waypoints),
      travelMode: google.maps.TravelMode.DRIVING,
    })
    .then((res) => {
      directionsRenderer.setDirections(res);
    })
    .catch(console.log);
}

function arrToWaypoint(
  arr: Array<Array<number>> | undefined
): google.maps.DirectionsWaypoint[] {
  let res = [];
  for (let elem of arr as number[][]) {
    res.push({ location: new google.maps.LatLng(elem[0], elem[1]) });
  }

  return res;
}

function arrToObj(arr: Array<Array<number>> | undefined): google.maps.LatLng[] {
  let res: Array<google.maps.LatLng> = [];
  res.push(new google.maps.LatLng(from[0], from[1]));
  for (let elem of arr as number[][]) {
    res.push(new google.maps.LatLng(elem[0], elem[1]));
  }
  res.push(new google.maps.LatLng(to[0], to[1]));

  return res;
}

function loadCrimeData() {
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
