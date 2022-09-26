import {useRouter, useRuntimeConfig, useState} from "nuxt/app";

function auth (){
    const BASE_URL = useRuntimeConfig().public.BASE_URL

    const user = useState('user' , () => null)
    const userInfos = useState('userInfos' , () => null)
    const requestHeaders = useState('requestHeaders' , () => {})
    const isLoggedIn = useState('loggedIn', () => false)
    const router = useRouter()

    if(process.client){
        let user_local = localStorage.getItem('user')
        console.log("UUUUSEER")
        console.log(user_local === "null")
        if(user_local === "null"){
            user.value = null
            isLoggedIn.value = false
        }
        else {
            try{
                user.value = JSON.parse(user_local)
                isLoggedIn.value = true
                requestHeaders.value = {Authorization : user.value.token}
            }catch(ex){
                user.value = null
            }
        }

        user.value = user_local
    }

    async function login({email, password}) {
        await $fetch(BASE_URL + 'api/auth/login', {
            method: 'POST',
            body: {
                email : email,
                password : password
            }
        }).then( response => {
            let toStore = {
                id: response.id,
                token: response.accessToken
            }
            localStorage.setItem("user" , JSON.stringify(toStore))
            user.value = toStore
            requestHeaders.value = {Authorization : user.value.token}
            getUserInfos()
            router.push("/")
            isLoggedIn.value = true
        }).catch(e => { console.log(e),isLoggedIn.value = false})

    }

    async function getUserInfos(){
        console.log("USER INFOS")
        await $fetch(BASE_URL + 'api/auth/info', {
            method: 'POST',
            body:  user.value.token,
        }).then( response => {
            console.log(response)
            userInfos.value = response
        }).catch(e => { console.log(e)})
    }

    function logout() {
        user.value = null
        isLoggedIn.value = false
        localStorage.setItem("user" , JSON.stringify(user.value))
        userInfos.value = {}
        router.push("/login")

    }

    return {
        requestHeaders,
        isLoggedIn,
        login,
        logout
    }
}

export default auth
