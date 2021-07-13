<template>
  <a-spin :spinning="loading">
    <div class="brand">
      <a-card title="Search" class="card-brand">
        <brandFilter @search="search" />
      </a-card>
      <a-card title="Brand List" class="card-brand-list">
        <a-button slot="extra" type="primary" @click="openModal"
          ><a-icon type="plus"
        /></a-button>
        <brandTable
          :brands="brands"
          @editBrand="editBrand"
          @deleteBrand="deleteBrand"
        />
      </a-card>
    </div>
    <brandModal ref="modalBrand" @refreshBrand="refreshBrand" />
  </a-spin>
</template>
<script>
export default {
  layout: 'admin',
  data() {
    return {
      visible: false,
      loading: false,
      brands: [],
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
        const result = await this.$api.brandList({
          headers: {
            Authorization: this.$auth.$storage.getUniversal('token').token,
          },
        })
        this.data = [...result.content]
        this.transformData(this.data)
      } catch (e) {
        console.log(e)
      } finally {
        this.loading = false
      }
    },
    search(obj) {
      const data = [...this.data].filter((item) => {
        return item.brandName.toLowerCase().includes(obj.search.toLowerCase())
      })
      this.transformData(data)
    },
    transformData(data) {
      this.brands = [...data].reduce((acc, item, index) => {
        const obj = {
          key: index + 1,
          id: item.brandID,
          name: item.brandName,
          desc: item.brandDesc,
          status: item.status,
        }
        acc.push(obj)
        return acc
      }, [])
      console.log(this.brands)
    },
    openModal() {
      this.$refs.modalBrand.show()
    },
    async refreshBrand() {
      await this.loadPage()
    },
    editBrand(id) {
      this.$refs.modalBrand.showEdit(id)
    },
    deleteBrand(id) {
      this.$confirm({
        title: 'Do you want delete brand',
        onOk: async () => {
          try {
            await this.$api.deleteBrand(id, {
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
