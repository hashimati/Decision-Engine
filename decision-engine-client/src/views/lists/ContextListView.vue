<script scoped>
  import ContextService from '@/assets/services/ContextService'

  export default {
    data() {
      return {
        contextsList: [],
        newContext: {},
      };
    },
    methods:{
      saveContext(){
                  //const contextService = new ContextService();
                  console.log("Saving Context");
                  ContextService.create(this.newContext, null);
  
              },
              findAllContext(){
                  console.log("findAll Contexts");
                 // const contextService = new ContextService();
                  ContextService.findAll(r=>{
                      this.contextsList = r.data;
                  });
              },
              showContext(id)
              {
                  console.log(id);
                  this.$router.push({
  
                      path:"/context/"+id,
                     });
              }
  
    }, 
    beforeMount(){
      this.findAllContext();
    }
  };
  </script>
  

<template>
  <div>
    <div class="row g-3">
      <div class="col-sm-12">
        <h3>Context</h3>
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
          data-bs-target="#contextModal"
        >
          Add
        </button>
      </div>
      <div class="col-sm-1">
        <button class="btn btn-primary" v-on:click="findAllContext">Load</button>
      </div>
      <div>
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">name</th>
              <th scope="col">description</th>
              <th scope="col">status</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="x in contextsList">
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
                {{ x.status }}
              </th>
              <th>
                <button
                  class="btn-primary btn btn-secondary"
                  v-on:click="showContext(x.id)">
                  Show
                </button>
              </th>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="modal fade" id="contextModal" tabindex="-1" aria-labelledby="employerModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">

            <h5 class="modal-title" id="exampleModalLabel">Create A Context</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form class="row g-3">
              <div class="col-md-12">
                <label for="contextName" class="form-label">Name</label>
                <input type="text" class="form-control" id="contextName" v-model="newContext.name"/>
              </div>
              <div class="col-md-12">
                <label for="contextDescription" class="form-label">Description</label>
                <textarea type="text" rows="6" class="form-control" id="contextDescription" v-model="newContext.description"/>
              </div>
              <div class="col-md-12">
                <label for="contextDescription" class="form-label">Status</label>
                <select type="text" rows="6" class="form-control" id="contextStatus" v-model="newContext.status">
                  <option>Unverified</option><option>Verified</option>
                </select>
              </div>
              
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" v-on:click="saveContext" data-bs-dismiss="modal">Save</button>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

