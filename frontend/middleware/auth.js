import auth from "~/composables/Authentication";
import {navigateTo} from "nuxt/app";

export default defineNuxtRouteMiddleware( function (){
    const {isLoggedIn} = auth();
    if(!isLoggedIn){
        return navigateTo("/")
    }
})
