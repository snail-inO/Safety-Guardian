import axios from "axios";

const URL = "http://localhost:8080/route/calculate?";

export interface Route {
    waypoints: Array<Array<number>> | undefined;
    avgRisk: number | undefined;
}

export async function calculateRoute(from: Array<number>, to: Array<number>) : Promise<Route> {
    const fromURL = coordsToURL(from);
    const toURL = coordsToURL(to);
    
    let res : Route = {
        waypoints: undefined,
        avgRisk: undefined
    };
    await axios.get(URL + "from="+ fromURL +"&to=" + toURL)
    .then((response) => {
        let data = response.data;
        let totalRisk = data.totalRisk;
        let waypoints : Array<Array<number>> = [];
        for (let waypoint of data.waypoints) {
            waypoints.push(waypoint);
        }
        let avgRisk = totalRisk / waypoints.length;
        
        res = {waypoints, avgRisk};
        // console.log(res);
    }).catch(error => {
        console.log("Calculate route error: " + error);
    });

    return res;
}

function coordsToURL(coords: Array<number>): string {
    return coords[0] + "," + coords[1];
}