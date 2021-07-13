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
          :model="brand"
          :label-col="labelCol"
          :wrapper-col="wrapperCol"
        >
          <a-form-model-item label="brand Name">
            <a-input v-model="brand.brandName" placeholder="Enter brand Name" />
          </a-form-model-item>
          <a-form-model-item label="brand Code">
            <a-input v-model="brand.brandCode" placeholder="Enter brand Code" />
          </a-form-model-item>
          <a-form-model-item label="brand Desc">
            <a-input v-model="brand.brandDesc" placeholder="Enter brand Desc" />
          </a-form-model-item>
          <a-form-model-item v-if="action === 'edit'" label="Status">
            <a-select v-model="brand.status" placeholder="Select Status">
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
      brandID: '',
      title: '',
      action: '',
      brand: {
        brandName: '',
        brandCode: '',
        brandDesc: '',
        status: '1',
      },
    }
  },
  methods: {
    show() {
      this.brandID = ''
      this.action = 'create'
      this.title = 'Create brand'
      this.visible = true
    },
    async showEdit(id) {
      try {
        this.loading = true
        this.action = 'edit'
        this.title = 'Edit brand'
        this.visible = true
        const result = await this.$api.getBrand(id, {
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        this.brandID = result.brandID
        this.brand = {
          brandName: result.brandName,
          brandCode: result.brandCode,
          brandDesc: result.brandDesc,
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
        if (this.brandID !== '') {
          await this.$api.updateBrand(this.brandID, this.brand, {
            headers: {
              Authorization: this.$auth.$storage.getUniversal('token').token,
            },
          })
          this.$message.success(`Update brand Successfully!`)
        } else {
          await this.$api.addBrand(this.brand, {
            headers: {
              Authorization: this.$auth.$storage.getUniversal('token').token,
            },
          })
          this.$message.success(`Add brand Successfully!`)
          this.brand = {
            brandName: '',
            brandCode: '',
            brandDesc: '',
            status: '1',
          }
        }
        this.$emit('refreshBrand')
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
      this.brand = {
        brandName: '',
        brandCode: '',
        brandDesc: '',
        status: '1',
      }
    },
  },
}
</script>
