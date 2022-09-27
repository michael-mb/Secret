import {defineStore} from 'pinia';

const BASE_URL = "http://localhost:8090"

const state = () => ({
    articles : [],
    selectedArticle: undefined
})

const getters = {
    getArticles : (state) => state.articles,
}

const actions = {
     fetchArticles(token){
         $fetch(BASE_URL + '/api/article/', {
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
    createArticle(title, text, date, ownerId){
        $fetch(BASE_URL + '/api/article/', {
            method: 'POST',
            body: {
                title: title,
                text: text,
                date: date,
                ownerID: ownerId
            }
        }).then( response => {
            this.articles.push(response)
            window.location = "/"
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
            window.location = "/"
        }).catch(e => {
            console.log(e)
        })
    },
    selectArticle(id){
        this.selectedArticle = this.articles.find( elem => Number(elem.id) === Number(id))
    }
}

const useArticleStore = defineStore('articleStore' , {
    state,
    getters,
    actions
});

export default useArticleStore
