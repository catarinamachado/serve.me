import axios from 'axios'

const API_URL = 'http://25.113.37.183:8083/api'

export default axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json'
  }
})