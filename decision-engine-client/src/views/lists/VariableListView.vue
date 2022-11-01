<script scoped>
    import VariableService from '@/assets/services/VariableService' 
    import ContextService from '@/assets/services/ContextService'

    export default {
      data() {
        return {
          variablesList: [],
          newVariable: {},
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
        saveVariable(){
                    //const VariableService = new VariableService();
                    console.log("Saving Variable");
                    VariableService.create(this.newVariable, null);
    
                },
                findAllVariable(){
                    console.log("findAll Variables");
                   // const VariableService = new VariableService();
                    VariableService.findAll(r=>{
                        this.variablesList = r.data;
                    });
                },
                showVariable(id)
                {
                    console.log(id);
                    this.$router.push({
    
                        path:"/variable/"+id,
                       });
                }
    
      }, 
      beforeMount(){
        this.findAllContextNames(); 
        this.findAllVariable();
      }
    };
    </script>
    
  
  <template>
    <div>
      <div class="row g-3">
        <div class="col-sm-12">
          <h3>Variable</h3>
        </div>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            placeholder="search"
            aria-label="search"
          />
        </div>
  
        <div class="col-sm-1">
          <button
            class="btn btn-primary"
            data-bs-toggle="modal"
            data-bs-target="#variableModal"
          >
            Add
          </button>
        </div>
        <div class="col-sm-1">
          <button class="btn btn-primary" v-on:click="findAllVariable">Load</button>
        </div>
        <div>
          <table class="table table-striped table-hover">
            <thead>
              <tr>
                <th scope="col">ID</th>
                <th scope="col">name</th>
                <th scope="col">description</th>
                <th scope="col">context</th>
                <th scope="col">status</th>
                <th scope="col">Action</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="x in variablesList">
                <th scope="row">
                  {{ x.id }}
                </th>
                <th scope="row">
                  {{ x.name }}
                </th>
                <th scope="row">
                  {{ x.description }}
                </th>
                <th scope="row">
                  {{ x.context }}
                </th>
                <th scope="row">
                  {{ x.status }}
                </th>
                <th>
                  <button
                    class="btn-primary btn btn-secondary"
                    v-on:click="showVariable(x.id)">
                    Show
                  </button>
                </th>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
  
      <div class="modal fade" id="variableModal" tabindex="-1" aria-labelledby="employerModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
  
              <h5 class="modal-title" id="exampleModalLabel">Create A Variable</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <form class="row g-3">
                <div class="col-md-12">
                  <label for="variableName" class="form-label">Name</label>
                  <input type="text" class="form-control" id="variableName" v-model="newVariable.name"/>
                </div>
                <div class="col-md-12">
                  <label for="variableDescription" class="form-label">Description</label>
                  <textarea type="text" rows="6" class="form-control" id="variableDescription" v-model="newVariable.description"/>
                </div>
                <div class="col-md-12">
                <label for="variableDescription" class="form-label">Type</label>
                <select class="form-control" id="variableType" v-model="newVariable.type">
                    <option>integer</option>
                    <option>decimal</option>
                    <option>string</option>
                    <option>date</option>
                </select>

              </div>
              <div class="col-md-12">
                <label for="variableContext" class="form-label">Context</label>
                <select class="form-control" id="variableContext" v-model="newVariable.context" >
                    <option v-for="x in contextNames">{{ x }}</option>
                </select>
              </div>
                <div class="col-md-12">
                  <label for="variableDescription" class="form-label">Status</label>
                  <select type="text" rows="6" class="form-control" id="variableStatus" v-model="newVariable.status">
                    <option>Unverified</option><option>Verified</option>
                  </select>
                </div>
                
              </form>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary" v-on:click="saveVariable" data-bs-dismiss="modal">Save</button>
            </div>
          </div>
        </div>
      </div>
  
    </div>
  </template>
  
  