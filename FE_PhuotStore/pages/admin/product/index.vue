<template>
  <a-spin :spinning="loading">
    <div class="brand">
      <a-card title="Search" class="card-brand">
        <productFilter @search="search" />
      </a-card>
      <a-card title="Product List" class="card-brand-list">
        <a-button slot="extra" type="primary" @click="openModal"
          ><a-icon type="plus"
        /></a-button>
        <productTable
          :products="products"
          @editProduct="editProduct"
          @deleteProduct="deleteProduct"
        />
      </a-card>
    </div>
    <productModal ref="modalProduct" @refreshProduct="refreshProduct" />
  </a-spin>
</template>
<script>
export default {
  layout: 'admin',
  data() {
    return {
      visible: false,
      loading: false,
      products: [],
      columns: [],
    }
  },
  created() {
    this.loadPage()
  },
  methods: {
    async loadPage() {
      this.loading = true
      try {
        const result = await this.$api.productList({
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        this.data = [...result.content]
        console.log(result.content)
        this.transformData(this.data)
      } catch (e) {
        console.log(e)
      } finally {
        this.loading = false
      }
    },
    search(obj) {
      const data = [...this.data].filter((item) => {
        return item.productName.toLowerCase().includes(obj.search.toLowerCase())
      })
      this.transformData(data)
    },
    transformData(data) {
      this.products = [...data].reduce((acc, item, index) => {
        const obj = {
          key: index + 1,
          id: item.productID,
          name: item.productName,
          desc: item.productDesc,
          status: item.status,
          create_at: item.createAt,
          discount: item.discount,
          price: item.price,
          productimg: item.productImg,
          qty: item.qty,
        }
        acc.push(obj)
        return acc
      }, [])
    },
    openModal() {
      this.$refs.modalProduct.show()
    },
    async refreshProduct() {
      await this.loadPage()
    },
    editProduct(id) {
      this.$refs.modalProduct.showEdit(id)
    },
    deleteProduct(id) {
      this.$confirm({
        title: 'Do you want delete product',
        onOk: async () => {
          try {
            await this.$api.deleteProduct(id, {
              headers: {
                Authorization: this.$auth.$storage.getUniversal('token').token,
              },
            })
            this.$message.success(`Delete Successfully!`)
          } catch (e) {
            if (e.response) {
              this.$message.warning(`Delete Fail!`)
            }
          } finally {
            await this.loadPage()
          }
        },
        onCancel() {},
      })
    },
  },
}
</script>
