<template>
  <header role="banner">
    <div class="top-bar">
      <div class="container">
        <div class="row">
          <div class="col-9 social">
            <a href="https://github.com/michael-mb"><span class="fa fa-github"></span></a>
          </div>
        </div>
      </div>
    </div>

    <div class="container logo-wrap">
      <div class="row pt-5">
        <div class="col-12 text-center">
          <a class="absolute-toggle d-block d-md-none" data-toggle="collapse" href="#navbarMenu" role="button" aria-expanded="false" aria-controls="navbarMenu"><span class="burger-lines"></span></a>
          <h1 class="site-logo"><a href="/">Secret</a></h1>
        </div>
      </div>
    </div>

    <nav class="navbar navbar-expand-md  navbar-light bg-light">
      <div class="container">


        <div class="collapse navbar-collapse" id="navbarMenu">
          <ul class="navbar-nav mx-auto">
            <li class="nav-item">
              <NuxtLink class="nav-link" to="/">Home</NuxtLink>
            </li>

            <li class="nav-item">
              <NuxtLink v-if="!isLoggedIn" class="nav-link" to="/login">Login</NuxtLink>
            </li>

            {{isLoggedIn}}
            <li class="nav-item">
              <NuxtLink v-if="isLoggedIn" class="nav-link" @click="logout">Logout</NuxtLink>
            </li>

          </ul>

        </div>
      </div>
    </nav>
  </header>
</template>

<script setup>
import auth from "../composables/Authentication";

const {isLoggedIn , logout} = auth()

</script>

<style scoped>
a.nuxt-link-active {

}

.router-link-exact-active {
  font-weight: bold;
  color: #6611f2ad !important;
}

.nav-link{
  cursor: pointer;
}
</style>
