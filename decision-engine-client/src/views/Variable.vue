<template>
    
    <div>
        <form class="row g-3">
              <div class="col-md-12">
                <label for="variableName" class="form-label">Name</label>
                <input type="text" class="form-control" id="variableName" v-model="c.name"/>
              </div>
              <div class="col-md-12">
                <label for="variableDescription" class="form-label">Description</label>
                <textarea type="text" rows="6" class="form-control" id="variableDescription" v-model="c.description"/>
              </div>
              <div class="col-md-12">
                <label for="variableDescription" class="form-label">Type</label>
                <select class="form-control" id="variableType" v-model="c.type">
                    <option>integer</option>
                    <option>decimal</option>
                    <option>string</option>
                    <option>date</option>
                </select>
              </div>
              <div class="col-md-12">
                <label for="variableContext" class="form-label">Context</label>
                <select class="form-control" id="variableContext" v-model="c.context" >
                    <option v-for="x in contextNames">{{ x }}</option>
                </select>
              </div>
              <div class="col-md-12">
                <label for="variableDescription" class="form-label">Status</label>
                <select type="text" rows="6" class="form-control" id="variableStatus" v-model="c.status">
                  <option>Unverified</option><option>Verified</option>
                </select>
              </div>
              
            </form>
            <div class = "row g-3">
                <button type="button" class="btn btn-danger" v-on:click="deleteVariable" >Delete</button>
                <button type="button" class="btn btn-warning" v-on:click="updateVariable">Update</button>

            </div>
    </div>

</template>
<script scoped>

    import VariableService from '@/assets/services/VariableService';
    import ContextService from '@/assets/services/ContextService'

    export default{

         data() {
                return {
                    c: {}, 
                    contextNames:[]


                };
    },
        methods:{
            findAllContextNames(){
            console.log("Find All Context names"); 
            ContextService.findAllNames(r=>{
                this.contextNames = r.data;
            }); 

            console.log(this.contextNames[0]);
            console.log(this.contextNames[1]);
        },
            findById(id)
            {
                console.log("Loading variable " + id); 
                VariableService.findById(id,r=>{
                this.c = r.data; 
            });
                //console.log(this.c.name); 
            },

            updateVariable(){
                VariableService.update(this.c.id, this.c, r=>{
                    console.log(r.data); 
                })
            }, 
            deleteVariable(){
                VariableService.delete(this.c.id, r=>{
                    console.log(r.data); 
                })
            }
        },


        created(){
            console.log("loading variable");
            this.findById(this.$route.params.id); 
            this.findAllContextNames();    
        }
    }
</script>