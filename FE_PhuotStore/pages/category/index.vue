<template>
  <a-spin :spinning="loading">
  <div class="category">
    <a-card title="Search" class="card-category">
      <categoryFilter @search="search"/>
    </a-card>
    <a-card title="Category List" class="card-category-list">
      <a-button slot="extra" type="primary" @click="openModal"><a-icon type="plus" /></a-button>
      <categoryTable :categories="categories" @editCategory="editCategory" @deleteCategory="deleteCategory"/>
    </a-card>
  </div>
  <categoryModal ref="modalCategory" @refreshCategory="refreshCategory"/>
  </a-spin>
</template>
<script>
export default {
  data(){
    return {
      visible : false,
      loading : false,
      categories : [],
      columns : [],
    }
  },
  created() {
    this.loadPage()
  },
  methods : {
    async loadPage(){
      this.loading = true
      try{
        const result = await this.$api.categoryList({
          headers: {
            'Authorization': this.$auth.$storage.getUniversal('token')
          }
        })
        this.data = [...result.content]
        this.transformData(this.data)
      }catch (e) {
        console.log(e)
      }finally {
        this.loading = false
      }
    },
    search(obj){
      const data = [...this.data].filter(item => {
          return item.categoryName.toLowerCase().includes(obj.search.toLowerCase())
      })
      this.transformData(data)
    },
    transformData(data){
      this.categories = [...data].reduce((acc,item,index) => {
        const obj = {
          key : index+1,
          id : item.categoryID,
          name : item.categoryName,
          desc : item.categoryDesc,
          status : item.status,
        }
        acc.push(obj)
        return acc
      },[])
    },
    openModal(){
      this.$refs.modalCategory.show()
    },
    async refreshCategory(){
      await this.loadPage()
    },
    editCategory(id){
      this.$refs.modalCategory.showEdit(id)
    },
    deleteCategory(id){
      this.$confirm({
        title: 'Do you want delete category',
        onOk : async () => {
          try{
            await this.$api.deleteCategory(id,{
              headers: {
                'Authorization': this.$auth.$storage.getUniversal('token')
              }
            })
            this.$message.success(`Delete Successfully!`);
          }catch (e) {
            if(e.response){
              this.$message.warning(`Delete Fail!`);

            }
          }finally {
            await this.loadPage()
          }

        },
        onCancel() {},
      });
    }
  },
}
</script>

