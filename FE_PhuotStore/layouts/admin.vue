<template>
  <a-layout id="components-layout-demo-custom-trigger">
    <layoutSidebarAdmin />
    <a-layout>
      <layoutHeaderAdmin />
      <a-layout-content
        :style="{
          margin: '24px 16px',
          padding: '24px',
          background: '#fff',
          minHeight: '280px',
        }"
      >
        <div>
          <featureBreadCrum />
          <Nuxt />
        </div>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script>
export default {
  middleware: 'authenticate',
}
</script>
