<template>
  <section class="site-section pt-5">
    <div class="container">
      <div class="row blog-entries" v-if="articleStore.selectedArticle">
        <div class="col-md-12 col-lg-8 main-content">
          <div class="row">
            <div class="col-md-12">
              <div class="post-meta">
                <span class="mr-2">{{articleStore.selectedArticle.date}}</span> • </div>

              <h2 class="mb-4">{{articleStore.selectedArticle.title}}</h2>
              <div v-html="marked(articleStore.selectedArticle.text)" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import useArticleStore from "../../store/article";
import useAuthStore from "../../store/auth";
import {marked} from 'marked';

const route = useRoute()
const router = useRouter()

const id = route.params.id

const authStore = useAuthStore()
const articleStore = useArticleStore()


onMounted(() => {
  if(!authStore.isLoggedIn)
    router.push("/login")

  articleStore.fetchArticles(authStore.user.token)

  if(articleStore.selectedArticle === undefined)
    router.push("/")

})

if(articleStore.articles.length > 0){
  articleStore.selectArticle(id)
}

</script>

<style scoped>

</style>
