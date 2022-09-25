<template>
  <section class="site-section py-sm">
    <div class="container">
      <div class="row mb-4">
        <div class="col-md-6">
          <h1>Login</h1>
        </div>
      </div>
      <div class="row blog-entries">
        <div class="col-md-12 col-lg-8 main-content">

          <form action="#" method="post">
            <div class="row">
              <div class="col-md-12 form-group">
                <label for="email">Email</label>
                <input id="email" class="form-control" type="email">
              </div>
              <div class="col-md-12 form-group">
                <label for="phone">Password</label>
                <input id="phone" class="form-control " type="password">
              </div>
            </div>

            <div class="row">
              <div class="col-md-6 form-group">
                <input type="submit" value="Login" class="btn btn-primary">
              </div>
            </div>
          </form>

          <p>Don't have an account yet? Create one by clicking <NuxtLink to="register">here</NuxtLink> </p>

        </div>

        <Michael_Meta/>

      </div>
    </div>
  </section>
</template>

<script setup>

import Michael_Meta from "../components/Michael_Meta";
</script>

<style scoped>

</style>
