import axios from 'axios';
import { environment } from '../Environment';

const LeagueClient = axios.create({
  baseURL: environment.leagueContext,
  headers: {
    'Content-Type': 'application/json'
  },
  withCredentials: true
});

export default LeagueClient;