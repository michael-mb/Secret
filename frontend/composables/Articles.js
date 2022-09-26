import {useRuntimeConfig} from "nuxt/app";
import authentication from "~/composables/Authentication";

function articles (){
    const BASE_URL = useRuntimeConfig().public.BASE_URL
    const {requestHeaders} = authentication()

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
        getArticles
    }
}
export default articles
