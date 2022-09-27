<template>
  <section class="site-section pt-5">
    <div class="container">

      <div class="row blog-entries">
        <div class="col-md-12 col-lg-8 main-content">

          <div class="row">
            <div class="col-md-12">
              <h2 class="mb-4">I'm {{authStore.userInfos.firstname}} {{authStore.userInfos.lastname}}</h2>
              <p>Email : {{authStore.userInfos.email}}</p>
              <p>GrantedAuthorities: <span class="mr-2" v-for="auth in authStore.userInfos.authorities" :key="auth.authority">
                {{auth.authority}} </span> </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import useAuthStore from "../store/auth";

const authStore = useAuthStore()
</script>

<style scoped>

</style>
