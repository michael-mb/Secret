import {defineStore} from 'pinia';

const BASE_URL = "http://localhost:8090"

const state = () => ({
    articles : []
})

const getters = {
    getArticles : (state) => state.articles,
}

const actions = {
    async fetchArticles(token){
        await $fetch(BASE_URL + '/api/article/', {
            method: 'GET',
            headers : {
                "Authorization" : token
            }
        }).then( response => {
            console.log(response)
            this.articles = response
        }).catch(e => {
            console.log(e)
        })
    },

    deleteArticle(id,token){
         $fetch(BASE_URL + '/api/article/?id='+id, {
            method: 'DELETE',
            headers : {
                "Authorization" : token
            }
        }).then( response => {
            console.log(response)
            this.articles = this.articles.filter( elem => {
                return elem.id !== id
            })
        }).catch(e => {
            console.log(e)
        })
    }
}

const useArticleStore = defineStore('articleStore' , {
    state,
    getters,
    actions
});

export default useArticleStore
