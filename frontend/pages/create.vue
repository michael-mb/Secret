<template>
  <section class="site-section py-sm">
    <div class="container">
      <div class="row mb-4">
        <div class="col-md-6">
          <h1>Post a new Article</h1>
        </div>
      </div>
      <div class="row blog-entries">
        <div class="col-md-12 col-lg-8 main-content">
          <div class="row">
            <div class="col-md-12 form-group">
              <label for="title">Title</label>
              <input id="title" class="form-control" type="text" v-model="createFrom.title">
            </div>

            <div class="col-md-12 form-group">
              <label for="text">PREVIEW</label>
              <br>
              <div v-html="marked(createFrom.text)"></div>
            </div>
            <div class="col-md-12 form-group">
              <label for="text">Text</label>
              <textarea class="form-control text-area" id="text" v-model="createFrom.text"></textarea>
            </div>

          </div>

          <div class="row">
            <div class="col-md-6 form-group">
              <input type="submit" value="POST" class="btn btn-primary" @click="createArticle">
            </div>
          </div>
        </div>
        <Michael_Meta/>
      </div>
    </div>
  </section>
</template>

<script setup>
import {marked} from 'marked';
import useAuthStore from "../store/auth";
import useArticleStore from "../store/article";

const authStore = useAuthStore()
const articleStore = useArticleStore()

const createFrom = ref({
  title : '...',
  text: '# Try to write with Markdown ✨'
})

function createArticle(){
  let date = new Date();
  date = date.getDate()+ '.' + (date.getMonth() + 1 ) + '.'+date.getFullYear()
  articleStore.createArticle(createFrom.value.title, createFrom.value.text, date, authStore.user.id)
}


</script>

<style scoped>
.text-area{
 min-height: 200px !important;
}
</style>
