<template>
  <section class="site-section py-sm mh-400" >
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <h2 class="mb-4">Latest Posts</h2>
        </div>
      </div>

      <div class="row blog-entries">
        <div class="col-md-12 col-lg-12 main-content">
          <div class="row">
            <div class="col-md-4" v-for="article in articleStore.articles" :key="article.id">
              <span class="delete-cross" @click="deleteById(article.id)"> ❌</span>

                <div class="blog-content-body">
                  <div class="post-meta">
                    <span class="mr-2">{{article.date}} </span> &bullet;
                  </div>
                  <a href="/" class="blog-entry">
                    <h2>{{article.title}}</h2>
                  </a>
                </div>
            </div>
          </div>
        </div>

      </div>
    </div>
  </section>
</template>

<script setup>
import useAuthStore from "../store/auth";
import useArticleStore from "../store/article";

const authStore = useAuthStore()
const articleStore = useArticleStore()

onMounted(() => {
  if(!authStore.isLoggedIn)
    route.push("/login")

  articleStore.fetchArticles(authStore.user.token)
})

function deleteById(id){
  if(confirm("Do you really want to delete this article ?"))
    articleStore.deleteArticle(id, authStore.user.token)
}
</script>

<style scoped>
.mh-400{
  min-height: 405px;
}

.delete-cross {
  cursor: pointer;
  font-size: 12px;
  position: absolute;
  right: 1.5rem;
  top: 0.2rem;
}
</style>
