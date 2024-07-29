import React from "react";
import { useEffect, useState } from "react";
import axios from "axios";
import moment from 'moment';
import {baseURL} from '../../Constant';
import "./Main.css";
function Main() {
  const [flightData, setFlightData] = useState([]);
  useEffect(() => {
    // Initially fetching flight data
    getData();

    const intervalId = setInterval(() => {
      getData();
    }, 600000); 

    return () => clearInterval(intervalId);
  }, []); 
  const getData=async()=>{
    const response=await axios.get(baseURL+"/api/flights/getFlightsStatus");
    setFlightData(response.data);
  }
  console.log(flightData);
  return (
    <div className="container border border-light rounded tableContainer bg-light h-50 my-3">
      <table class="table_flight table_flight-light table_flight-hover flightStatusTable fw-bolder">
        <thead className="table-info" >
          <tr className="" style={{ backgroundColor: 'rgb(141, 212, 253)' }}>
            <th>Flight Number</th>
            <th>Airline</th>
            <th>Origin</th>
            <th>Destination</th>
            <th>Scheduled Departure</th>
            <th>ScheduledArrival</th>
            <th>Status</th>
            <th>Gate</th>
          </tr>
        </thead>
        <tbody>
        {flightData.map((row) => (
          <tr key={row.id}  className={row.cancelled ? 'cancelledFlight' : row.status=='Delayed' ?'delayedFlight':''}>
            <td>{row.flightNumber}</td>
            <td>{row.airline}</td>
            <td>{row.origin}</td>
            <td>{row.destination}</td>
            <td>{moment(row.scheduledArrival).format('MMMM Do YYYY, h:mm a')}</td>
            <td>{moment(row.scheduledDeparture).format('MMMM Do YYYY, h:mm a')}</td>
            <td>{row.status}</td>
            <td>{row.gateChange.newGate}</td>
          </tr>
        ))}
        </tbody>
      </table>
    </div>
  );
}

export default Main;
