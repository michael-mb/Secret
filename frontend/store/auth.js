import {defineStore} from 'pinia';

const BASE_URL = "http://localhost:8090"

let user

const state = () => ({
    user : user,
    userInfos : {},
    login_error : null
})

const getters = {
    getUser : (state) => state.user,
    getUserInfos: (state) =>  state.userInfos,
    isLoggedIn() {
        if(!this.user)
            return false
        else if(this.user.id === -1)
            return false
        return true
    },
}

const actions = {
     async login({email,password}){
         await $fetch(BASE_URL + '/api/auth/login', {
            method: 'POST',
            body: {
                email : email,
                password : password
            }
        }).then(response => {
            let toStore = {
                id: response.id,
                token: response.accessToken
            }
            localStorage.setItem("user" , JSON.stringify(toStore))
            this.user = toStore
            window.location = "/"
        }).catch(e => { console.log(e)
             this.login_error = "Password or Email are incorrect ! 🥲"
        })
    },

    async register (email, firstname, lastname, password) {
         console.log("REGISTER")
        await $fetch(BASE_URL + '/api/auth/register', {
            method: 'POST',
            body: {
                email : email,
                firstname : firstname,
                lastname : lastname,
                password : password,
                passwordAgain : password,
            }
        }).then(() => {
            this.login({email, password})
        }).catch(e => { console.log(e)})
    },
    async fetchUserInfos(){
        await $fetch(BASE_URL + '/api/auth/info', {
            method: 'POST',
            body:  user.token,
        }).then( response => {
            this.userInfos = response
        }).catch(e => { console.log(e)})
    },
    logout(){
        this.user = {
            id: -1,
            token: "",
        }
        localStorage.setItem("user" , JSON.stringify(this.user))
        this.userInfos = {}
    },
    setUserInfos(elem){
        console.log("ELEM: " +elem)
        this.userInfos = elem
    },
    initUser(){
        user = JSON.parse(localStorage.getItem('user'))
        if(!user){
            user = {
                id: -1,
                token: "",
            }
        }else {
            try{
                user = JSON.parse(localStorage.getItem('user'))
                //instance.defaults.headers.common['Authorization'] = user.token
            }catch(ex){
                user = {
                    id: -1,
                    token: "",
                }
            }
        }
        this.user = user
    }
}

const useAuthStore = defineStore('authStore' , {
    state,
    getters,
    actions
});

export default useAuthStore

