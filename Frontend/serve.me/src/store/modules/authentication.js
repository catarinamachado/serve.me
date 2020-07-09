import { CLIENT_AUTH_REQUEST, PROVIDER_AUTH_REQUEST, AUTH_ERROR, AUTH_SUCCESS, AUTH_LOGOUT } from '../action_calls/authentication'
import { USER_REQUEST } from '../action_calls/user'
import axios from 'axios'
import backend from '../consts'

const state = {
  token: localStorage.getItem('user-token') || '',
  status: ''
}

const getters = {
    isAuthenticated: state => !!state.token,
    getToken: state => state.token,
    authStatus: state => state.status
}

const mutations = {
  [CLIENT_AUTH_REQUEST]: state => {
    state.status = 'loading'
  },
  [PROVIDER_AUTH_REQUEST]: state => {
    state.status = 'loading'
  },  
  [AUTH_SUCCESS]: (state, token) => {
    state.status = 'success'
    state.token = token
  },
  [AUTH_ERROR]: (state, err) => {
    state.status = 'error: ' + err
  },
  [AUTH_LOGOUT]: state => {
    state.token = ''
  }
}

const actions = {
  [CLIENT_AUTH_REQUEST]: ({commit, dispatch}, user) => {
    return new Promise((resolve, reject) => { // The Promise used for router redirect in login
      commit(CLIENT_AUTH_REQUEST)
      axios({url: backend.URL + '/login/cliente', data: user, method: 'POST' })
        .then(resp => {
          if (resp.data.status > 0) {
            const token = resp.data.token
            localStorage.setItem('user-token', token) // store the token in localstorage
            commit(AUTH_SUCCESS, token)
            // you have your token, now log in your user :)
            let user = {
                nome: resp.data.nome,
                typeOf: 'client'
              }            
              dispatch(USER_REQUEST, user)
            resolve(resp)
          }
          else {
            const err = "login credentials invalid"
            commit(AUTH_ERROR, err)
            localStorage.removeItem('user-token') // if the request fails, remove any possible user token
            reject(err)
          }
        })
      })
  },
  [PROVIDER_AUTH_REQUEST]: ({commit, dispatch}, user) => {
    return new Promise((resolve, reject) => { // The Promise used for router redirect in login
      commit(PROVIDER_AUTH_REQUEST)
      //console.log(user);
      axios({url: backend.URL + '/login/prestador', data: user, method: 'POST' })
        .then(resp => {
          if (resp.data.status > 0) {
            const token = resp.data.token
            localStorage.setItem('user-token', token) // store the token in localstorage
            commit(AUTH_SUCCESS, token)
            // you have your token, now log in your user :)
            let user = {
                nome: resp.data.nome,
                typeOf: 'provider'
              }                 
            dispatch(USER_REQUEST, user)
            resolve(resp)
          }
          else {
            const err = "login credentials invalid"
            commit(AUTH_ERROR, err)
            localStorage.removeItem('user-token') // if the request fails, remove any possible user token
            reject(err)
          }
        })
      })
  },  
  [AUTH_LOGOUT]: ({commit}) => {
    return new Promise((resolve) => {
      commit(AUTH_LOGOUT)
      localStorage.removeItem('user-token') // clear your user's token from localstorage
      resolve()
    })
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
