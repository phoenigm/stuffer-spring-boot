import axios from 'axios'

export const AXIOS = axios.create({
    baseURL: 'http://localhost:8088/',
    headers: {
        'Access-Control-Allow-Origin': 'http://localhost:8080'
    }
});


const token = localStorage.getItem('token');
if (token) {
    AXIOS.defaults.headers.common['Authorization'] = 'Bearer ' + token
}
