import axios from "axios";

const URL = "http://localhost:8080/crime_data";
const SIZE = "?size=3000";

export default async function getCrimeData(): Promise<Array<[number, number, number]>> {
  let spots: Array<[number, number, number]> = [];
  let page: number = 1;
  let totalPages = 0;

  await axios
    .get(URL + SIZE + "&page=0")
    .then((response) => {
      let res = response.data;
      totalPages = res.page.totalPages;
      spots = extractCoords(res._embedded.crimeDataList);
    })
    .catch((error) => {
      console.log("Retrieve crime data error: " + error);
    });

  while (page < totalPages) {
    await axios
      .get(URL + SIZE + "&page=" + page.toString())
      .then((response) => {
        spots = spots.concat(extractCoords(response.data._embedded.crimeDataList));
      })
      .catch((error) => {
        console.log("Retrieve crime data error: " + error);
      });
    page++;
  }

  return spots;
}

function extractCoords(crimeDataList: Array<any>): Array<[number, number, number]> {
  let coords: Array<[number, number, number]> = [];
  for (let crimeData of crimeDataList) {
    coords.push([crimeData.y, crimeData.x, crimeData.crimeType.weight]);
  }

  return coords;
}
