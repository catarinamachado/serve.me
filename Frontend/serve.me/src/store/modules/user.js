import { USER_REQUEST, USER_SUCCESS, USER_ERROR } from '../action_calls/user'
import { AUTH_LOGOUT } from '../action_calls/authentication'

const state = {
  status: '',
  nome: localStorage.getItem('nome') || '',
  typeOf: localStorage.getItem('typeOf') || ''
}

const getters = {
  getNome: state => state.nome,
  getTypeOf: state => state.typeOf
}

const actions = {
  [USER_REQUEST]: ({commit}, user) => {
    commit(USER_REQUEST);
    localStorage.setItem('nome', user.nome);
    localStorage.setItem('typeOf', user.typeOf);
    commit(USER_SUCCESS, user);
  },
  [AUTH_LOGOUT]: ({commit}) => {
    localStorage.removeItem('nome');
    localStorage.removeItem('typeOf');
    localStorage.removeItem('user-token');
    commit(AUTH_LOGOUT);
  }  
}

const mutations = {
  [USER_REQUEST]: (state) => {
    state.status = 'loading'
  },
  [USER_SUCCESS]: (state, user) => {
    state.status = 'success'
    state.nome = user.nome
    state.typeOf = user.typeOf
  },
  [USER_ERROR]: (state) => {
    state.status = 'error'
  },
  [AUTH_LOGOUT]: (state) => {
    state.status = 'not_loaded'
    state.nome = ''
    state.typeOf = ''
  }
}

export default {
  state,
  getters,
  actions,
  mutations,
}
