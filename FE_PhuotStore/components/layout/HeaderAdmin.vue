<template>
  <a-layout-header class="header-admin" style="background: #fff; padding: 0">
    <div class="user-management">
      <a-icon type="user" />
      <a-popover placement="bottom">
        <template slot="content">
          <div class="user-popover">
            <a-button>Change Password</a-button>
          </div>
          <div>
            <a-button @click="logout">Logout</a-button>
          </div>
        </template>
        <a-button class="user-name">Admin</a-button>
      </a-popover>
    </div>
  </a-layout-header>
</template>

<script>
export default {
  name: 'SidebarAdmin.vue',
  data() {
    return {
      collapsed: false,
    }
  },
  methods : {
    async logout(){
      this.$auth.$storage.removeUniversal('token')
      this.$message.success(`Successfully Logout !`);
      await this.$router.push('/login')
    }
  },

}
</script>
