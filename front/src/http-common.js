import axios from 'axios'

export default axios.create({
    baseURL: "/api",
    // headers: {
    //     'Access-Control-Allow-Origin': '*',
    //     'Content-Type': 'application/json'
    // }
    proxy: true
})