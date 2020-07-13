import axios from 'axios'
import backend from './store/consts'

const API_URL = backend.URL

export default axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json'
  }
})