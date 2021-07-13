<template>
  <div>
    <a-spin :spinning="loading">
      <a-modal
        :title="title"
        :visible="visible"
        :confirm-loading="confirmLoading"
        @ok="handleOk"
        @cancel="close"
      >
        <a-form-model
          :model="category"
          :label-col="labelCol"
          :wrapper-col="wrapperCol"
        >
          <a-form-model-item label="Category Name">
            <a-input
              v-model="category.categoryName"
              placeholder="Enter Category Name"
            />
          </a-form-model-item>
          <a-form-model-item label="Category Code">
            <a-input
              v-model="category.categoryCode"
              placeholder="Enter Category Code"
            />
          </a-form-model-item>
          <a-form-model-item label="Category Desc">
            <a-input
              v-model="category.categoryDesc"
              placeholder="Enter Category Desc"
            />
          </a-form-model-item>
          <a-form-model-item v-if="action === 'edit'" label="Status">
            <a-select v-model="category.status" placeholder="Select Status">
              <a-select-option value="1"> 1 </a-select-option>
              <a-select-option value="2"> 2 </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-form-model>
      </a-modal>
    </a-spin>
  </div>
</template>
<script>
export default {
  data() {
    return {
      labelCol: { span: 7 },
      wrapperCol: { span: 17 },
      visible: false,
      confirmLoading: false,
      loading: false,
      categoryID: '',
      title: '',
      action: '',
      category: {
        categoryName: '',
        categoryCode: '',
        categoryDesc: '',
        status: '1',
      },
    }
  },
  methods: {
    show() {
      this.categoryID = ''
      this.action = 'create'
      this.title = 'Create Category'
      this.visible = true
    },
    async showEdit(id) {
      try {
        this.loading = true
        this.action = 'edit'
        this.title = 'Edit Category'
        this.visible = true
        const result = await this.$api.getCategory(id, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        this.categoryID = result.categoryID
        this.category = {
          categoryName: result.categoryName,
          categoryCode: result.categoryCode,
          categoryDesc: result.categoryDesc,
          status: result.status,
        }
      } catch (e) {
        console.log(e.response)
      } finally {
        this.loading = false
      }
    },
    async handleOk(e) {
      try {
        this.confirmLoading = true
        if (this.categoryID !== '') {
          await this.$api.updateCategory(this.categoryID, this.category, {
            headers: {
              Authorization: this.$auth.$storage.getUniversal('token').token,
            },
          })
          this.$message.success(`Update Category Successfully!`)
        } else {
          await this.$api.addCategory(this.category, {
            headers: {
              Authorization: this.$auth.$storage.getUniversal('token').token,
            },
          })
          this.$message.success(`Add Category Successfully!`)
          this.category = {
            categoryName: '',
            categoryCode: '',
            categoryDesc: '',
            status: '1',
          }
        }
        this.$emit('refreshCategory')
      } catch (e) {
        if (e.response) {
          this.$message.warning(`Error please try agian!`)
        }
      } finally {
        this.confirmLoading = false
        this.visible = false
      }
    },
    close(e) {
      this.visible = false
      this.category = {
        categoryName: '',
        categoryCode: '',
        categoryDesc: '',
        status: '1',
      }
    },
  },
}
</script>
