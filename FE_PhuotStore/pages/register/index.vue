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
              'email',
              {
                rules: [
                  { required: true, message: 'Please enter your email!' },
                ],
              },
            ]"
            placeholder="Email Address"
          >
            <a-icon
              slot="prefix"
              type="email"
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
            Register
          </a-button>
          Or
          <nuxt-link to="/login">Already have account! Login</nuxt-link>
        </a-form-item>
      </a-form>
    </div>
  </a-spin>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
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
          this.register({
            username: values.username,
            email: values.email,
            password: values.password,
            role: ['user'],
          })
        }
      })
    },
    async register(user) {
      this.loading = true
      const result = await this.$api.register(user)
      this.loading = false
      if (result.message) {
        await this.$router.push('/login')
        this.$message.success(`${result.message} Please Login!`)
      } else {
        this.$message.warning('Have some error!')
      }
    },
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
