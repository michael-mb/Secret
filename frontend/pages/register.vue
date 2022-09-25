<template>
  <section class="site-section py-sm">
    <div class="container">
      <div class="row mb-4">
        <div class="col-md-6">
          <h1>Register</h1>
        </div>
      </div>
      <div class="row blog-entries">
        <div class="col-md-12 col-lg-8 main-content">

          <form action="#" method="post">
            <div class="row">
              <div class="col-md-12 form-group">
                <label for="firstname">Firstname</label>
                <input id="firstname" class="form-control" type="text">
              </div>
            </div>
            <div class="row">
              <div class="col-md-12 form-group">
                <label for="lastname">Lastname</label>
                <input id="lastname" class="form-control" type="text">
              </div>
            </div>
            <div class="row">
              <div class="col-md-12 form-group">
                <label for="email">Email</label>
                <input id="email" class="form-control" type="email">
              </div>
              <div class="col-md-12 form-group">
                <label for="password">Password</label>
                <input id="password" class="form-control " type="password">
              </div>
              <div class="col-md-12 form-group">
                <label for="repeat_password">Repeat Password</label>
                <input id="repeat_password" class="form-control " type="password">
              </div>
            </div>

            <div class="row">
              <div class="col-md-6 form-group">
                <input type="submit" value="Register" class="btn btn-primary">
              </div>
            </div>
          </form>

          <p>Already have an account ? Login by clicking <NuxtLink to="login">here</NuxtLink> </p>

        </div>

        <Michael_Meta/>

      </div>
    </div>
  </section>
</template>

<script setup>

</script>

<style scoped>

</style>
