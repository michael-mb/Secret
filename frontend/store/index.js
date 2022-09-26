import {defineStore} from 'pinia';

const state = () => ({
    user : {},
    userInfos : {}
})

const getters = {}

const actions = {}

export const useStore = defineStore('store' , {
    state,
    getters,
    actions
})
