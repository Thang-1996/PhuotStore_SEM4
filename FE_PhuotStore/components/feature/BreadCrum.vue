<template>
  <a-breadcrumb>
    <a-breadcrumb-item href="">
      <a-icon type="home" />
      <nuxt-link to="/dashboard">Dashboard</nuxt-link>
    </a-breadcrumb-item>
    <a-breadcrumb-item>
      <a-icon :type="icon" />
      <nuxt-link :to="path">{{ title }}</nuxt-link>
    </a-breadcrumb-item>
  </a-breadcrumb>
</template>

<script>
export default {
  props : {
    icon : {
      type : String,
      default : () => ''
    },
    path : {
      type : String,
      default : () => '/dashboard'
    },
    title : {
      type : String,
      default : () => ''
    }
  },
  name: 'BreadCrum.vue'
}
</script>

