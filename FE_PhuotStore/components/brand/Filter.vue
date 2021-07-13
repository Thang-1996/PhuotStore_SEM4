<template>
  <a-form-model layout="inline" :model="formFilter" @submit="handleSubmit" @submit.native.prevent>
    <a-form-model-item>
      <a-input v-model="formFilter.search" placeholder="Search">
        <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)" />
      </a-input>
    </a-form-model-item>
    <a-form-model-item>
      <a-select default-value="name" style="width: 200px" @change="handleChange">
        <a-select-option value="name">
          Name
        </a-select-option>
        <a-select-option value="id">
          id
        </a-select-option>
      </a-select>
    </a-form-model-item>
    <a-form-model-item>
      <a-button
        type="primary"
        html-type="submit"
      >
        Search
      </a-button>
    </a-form-model-item>
  </a-form-model>
</template>

<script>
export default {
  data() {
    return {
      formFilter: {
        search: '',
        filter: '',
      },
    };
  },
  methods: {
    handleSubmit(e) {
      this.$emit('search',this.formFilter);
    },
    handleChange(value) {
      this.formFilter.filter = value
    },
  },
};
</script>
