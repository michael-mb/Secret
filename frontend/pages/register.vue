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

          <div class="row">
            <div class="col-md-12 form-group">
              <label for="firstname">Firstname</label>
              <input id="firstname" class="form-control" type="text" v-model="registerForm.firstname">
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group">
              <label for="lastname">Lastname</label>
              <input id="lastname" class="form-control" type="text" v-model="registerForm.lastname">
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group">
              <label for="email">Email</label>
              <input id="email" class="form-control" type="email" v-model="registerForm.email">
            </div>
            <div class="col-md-12 form-group">
              <label for="password">Password</label>
              <input id="password" class="form-control " type="password" v-model="registerForm.password">
            </div>
            <div class="col-md-12 form-group">
              <label for="repeat_password">Repeat Password</label>
              <input id="repeat_password" class="form-control " type="password" v-model="registerForm.passwordAgain">
            </div>
          </div>
          <p class="error">{{error}}️</p>

          <div class="row">
            <div class="col-md-6 form-group">
              <input type="submit" value="Register" class="btn btn-primary" @click="register">
            </div>
          </div>


        <p>Already have an account ? Login by clicking <NuxtLink to="login">here</NuxtLink> </p>

        </div>

        <Michael_Meta/>

      </div>
    </div>
  </section>
</template>

<script setup>
import useAuthStore from "../store/auth";
const authStore = useAuthStore()
const registerForm = ref({
  firstname : "",
  lastname : "",
  email : "",
  password : "",
  passwordAgain: ""
})

const error = ref("")

function register(){
  error.value = ""

  console.log(registerForm.value)
  if(registerForm.value.email === '' || registerForm.value.firstname === '' || registerForm.value.lastname === ''
      || registerForm.value.password === '' ||  registerForm.value.password !== registerForm.value.passwordAgain){

   error.value = "Verify your form and send it back !  ⛔"
    return
  }

  authStore.register(registerForm.value.email, registerForm.value.firstname, registerForm.value.lastname , registerForm.value.password)
}
</script>

<style scoped>
.error{
  color: red;
}
</style>
