


<template>
    
    <div>
        <form class="row g-3">
              <div class="col-md-12">
                <label for="contextName" class="form-label">Name</label>
                <input type="text" class="form-control" id="contextName" v-model="c.name"/>
              </div>
              <div class="col-md-12">
                <label for="contextDescription" class="form-label">Description</label>
                <textarea type="text" rows="6" class="form-control" id="contextDescription" v-model="c.description"/>
              </div>
              <div class="col-md-12">
                <label for="contextDescription" class="form-label">Status</label>
                <select type="text" rows="6" class="form-control" id="contextStatus" v-model="c.status">
                  <option>Unverified</option><option>Verified</option>
                </select>
              </div>
              
            </form>
            <div class = "row g-3">
                <button type="button" class="btn btn-danger" v-on:click="deleteContext" >Delete</button>
                <button type="button" class="btn btn-warning" v-on:click="updateContext">Update</button>

            </div>
    </div>

</template>
<script scoped>

    import ContextService from '@/assets/services/ContextService';
    export default{

         data() {
                return {
                    c: {}


                };
    },
        methods:{
            findById(id)
            {
                console.log("Loading context " + id); 
                ContextService.findById(id,r=>{
                this.c = r.data; 
            });
                //console.log(this.c.name); 
            },

            updateContext(){
                ContextService.update(this.c.id, this.c, r=>{
                    console.log(r.data); 
                })
            }, 
            deleteContext(){
                ContextService.delete(this.c.id, r=>{
                    console.log(r.data); 
                })
            }
        },


        created(){
            console.log("loading context");
            this.findById(this.$route.params.id); 
            
        }
    }
</script>