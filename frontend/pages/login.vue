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
          <div class="row">
            <div class="col-md-12 form-group">
              <label for="email">Email</label>
              <input id="email" class="form-control" type="email" v-model="loginForm.email">
            </div>
            <div class="col-md-12 form-group">
              <label for="phone">Password</label>
              <input id="phone" class="form-control " type="password" v-model="loginForm.password">
            </div>
          </div>
          <p class="error"> {{authStore.login_error}}</p>
          <div class="row">
            <div class="col-md-6 form-group">
              <input type="submit" value="Login" class="btn btn-primary" @click="sendLoginForm">
            </div>
          </div>

          <p>Don't have an account yet? Create one by clicking <NuxtLink to="register">here</NuxtLink> </p>
        </div>
        <Michael_Meta/>
      </div>
    </div>
  </section>
</template>

<script setup>
import useAuthStore from "../store/auth";
const route = useRouter()

const authStore = useAuthStore()
const loginForm = reactive({
  password: "",
  email: ""
})

onMounted(() => {
  if(authStore.isLoggedIn)
    route.push("/")
})

function sendLoginForm(){
  authStore.login( { email : loginForm.email , password: loginForm.password})
}

</script>

<style scoped>
.error{
  color: red;
}
</style>
