import React from "react";
import axios from "axios";

const url = "http://localhost:8080/crime_data";

export default function getCrimeData(): void {
  let spots;
  axios
    .get(url)
    .then((response) => {
      console.log(response.data);
    })
    .catch((error) => {
      console.log("Retrieve crime data error: " + error);
    });
}
