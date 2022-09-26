import {useFetch, useRequestHeaders, useRouter, useRuntimeConfig} from "nuxt/app";

function auth (){
    const config = useRuntimeConfig()
    const BASE_URL = config.public.BASE_URL

    const user = useState('user' , () => null)
    const userInfos = useState('userInfos' , () => null)
    const requestHeaders = useState('requestHeaders' , () => {})
    const router = useRouter()

    if(process.client){
        let user_local = localStorage.getItem('user')
        if(!user_local)
            user.value = { id: -1, token: ""}
        else {
            try{
                user.value = JSON.parse(user_local)
                requestHeaders.value = {Authorization : user.value.token}
                // instance.defaults.headers.common['Authorization'] = user.token // ---> achten
            }catch(ex){
                user.value = {
                    id: -1,
                    token: "",
                }
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
            let toStore ={
                id: response.id,
                token: response.accessToken
            }
            localStorage.setItem("user" , JSON.stringify(toStore))
            user.value = toStore
            requestHeaders.value = {Authorization : user.value.token}
            getUserInfos()
        }).catch(e => { console.log(e)})

    }

    async function getUserInfos(){
        await $fetch(BASE_URL + 'api/auth/info', {
            method: 'POST',
            body:  user.value.token,
        }).then( response => {
            console.log("USER INFOS")
            console.log(response)
            userInfos.value = response


            getArticles()
        }).catch(e => { console.log(e)})
    }

    async function getArticles(){

        await $fetch(BASE_URL + 'api/article/', {
            server: false,
            headers: requestHeaders.value,
            method: 'GET',
        }).then( response => {
            console.log("GET ARTICLES")
            console.log(response)
        }).catch(e => {
            console.log(e)
        })
    }

    return {
        login
    }
}

export default auth
