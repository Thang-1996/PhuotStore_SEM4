<template>
  <a-spin :spinning="loading">
  <div class="login">
      <a-form
        id="components-form-demo-normal-login"
        :form="form"
        class="login-form"
        @submit="handleSubmit"
      >
        <a-form-item>
          <a-input
            v-decorator="[
            'username',
            {
              rules: [
                { required: true, message: 'Please input your username!' },
              ],
            },
            {
              initialValue: '',
            },
          ]"
            placeholder="Username"
          >
            <a-icon
              slot="prefix"
              type="user"
              style="color: rgba(0, 0, 0, 0.25)"
            />
          </a-input>
        </a-form-item>
        <a-form-item>
          <a-input
            v-decorator="[
            'password',
            {
              rules: [
                { required: true, message: 'Please input your Password!' },
              ],
            },
             {
              initialValue: '',
            },
          ]"
            type="password"
            placeholder="Password"
          >
            <a-icon
              slot="prefix"
              type="lock"
              style="color: rgba(0, 0, 0, 0.25)"
            />
          </a-input>
        </a-form-item>
        <a-form-item>
          <a-checkbox
            v-decorator="[
            'remember',
            {
              valuePropName: 'checked',
              initialValue: false,
            },
          ]"
          >
            Remember me
          </a-checkbox>
          <a class="login-form-forgot" href=""> Forgot password </a>
          <a-button type="primary" html-type="submit" class="login-form-button">
            Log in
          </a-button>
          Or
          <nuxt-link to="/register">Do not have account! Register now</nuxt-link>
        </a-form-item>
      </a-form>


  </div>
  </a-spin>
</template>

<script>
export default {
  layout: 'login',
  data(){
    return{
      loading : false,
    }
  },
  beforeCreate() {
    this.form = this.$form.createForm(this, { name: 'normal_login' })
  },
  methods: {
    handleSubmit(e) {
      e.preventDefault()
      this.form.validateFields((err, values) => {
        if (!err) {
          this.login({
            username : values.username,
            password : values.password
          })
        }
      })
    },
    async login(user){
      try{
        this.loading = true
        const result = await this.$api.login(user)
        this.$auth.$storage.setUniversal('token', `${result.tokenType} ${result.accessToken}`);
        await this.$router.push('/')
        this.$message.success(`Successfully Login!`);
      }catch (e) {
        if(e.response.status === 401){
          this.$message.warning(`Login Fail`);
        }
      }finally {
        this.form.value.resetFields()
        this.loading = false
      }
    }
  },
}
</script>
<style>
#components-form-demo-normal-login .login-form {
  max-width: 300px;
}
#components-form-demo-normal-login .login-form-forgot {
  float: right;
}
#components-form-demo-normal-login .login-form-button {
  width: 100%;
}
</style>
