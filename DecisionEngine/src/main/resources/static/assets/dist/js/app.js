


const contextComponent = {

    template: '<div class="row g-3">\n' +
        '  <div class="col-sm-12">\n' +
        '    <h3>Context</h3>\n' +
        '  </div>\n' +
        '  <div class="col-sm-10">\n' +
        '    <input type="text" class="form-control" placeholder="search" aria-label="search">\n' +
        '  </div>\n' +
        '\n' +
        '  <div class="col-sm-1">\n' +
        '    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#contextModal">Add</button>\n' +
        '\n' +
        '  </div>\n' +
        '  <div class="col-sm-1">\n' +
        '    <button class="btn btn-primary" v-on:click="findAllContext">Load</button>\n' +
        '\n' +
        '  </div>\n' +
        '  <div class="col-12">\n' +
        '    <table class="table table-striped table-hover">\n' +
        '      <thead>\n' +
        '      <tr>\n' +
        '        <th scope="col">\n' +
        '          ID\n' +
        '        </th>\n' +
        '        <th scope="col">\n' +
        '          name\n' +
        '        </th>\n' +
        '        <th scope="col">\n' +
        '          description\n' +
        '        </th>\n' +
        '        <th scope="col">\n' +
        '          status\n' +
        '        </th>\n' +
        '        <th scope="col">\n' +
        '          Action\n' +
        '        </th>\n' +
        '      </tr>\n' +
        '      </thead>\n' +
        '      <tbody>\n' +
        '      <tr v-for="x in contextsList">\n' +
        '        <th scope="row">\n' +
        '          {{x.id}}\n' +
        '        </th>\n' +
        '        <th scope="row">\n' +
        '          {{x.name}}\n' +
        '        </th>\n' +
        '        <th scope="row">\n' +
        '          {{x.description}}\n' +
        '        </th>\n' +
        '        <th scope="row">\n' +
        '          {{x.status}}\n' +
        '        </th>\n' +
        '        <th>\n' +
        '          <button class="btn-primary btn btn-secondary" v-on:click="showContext(x.id)">\n' +
        '            Show\n' +
        '          </button>\n' +
        '        </th>\n' +
        '\n' +
        '      </tr>\n' +
        '\n' +
        '      </tbody>\n' +
        '    </table>\n' +
        '  </div>\n' +
        '\n' +
        '  <div class="modal fade" id="contextModal" tabindex="-1" aria-labelledby="employerModalLabel" aria-hidden="true">\n' +
        '    <div class="modal-dialog">\n' +
        '      <div class="modal-content">\n' +
        '        <div class="modal-header">\n' +
        '          <h5 class="modal-title" id="exampleModalLabel">Create A Context</h5>\n' +
        '          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>\n' +
        '        </div>\n' +
        '        <div class="modal-body">\n' +
        '          <form class="row g-3">\n' +
        '            <div class="col-md-12">\n' +
        '              <label for="contextName" class="form-label">Name</label>\n' +
        '              <input type="text" class="form-control" id="contextName" v-model="newContext.name"/>\n' +
        '            </div>\n' +
        '            <div class="col-md-12">\n' +
        '              <label for="contextDescription" class="form-label">Description</label>\n' +
        '              <textarea type="text" rows="6" class="form-control" id="contextDescription" v-model="newContext.description"/>\n' +
        '            </div>\n' +
        '            <div class="col-md-12">\n' +
        '              <label for="contextDescription" class="form-label">Status</label>\n' +
        '              <select type="text" rows="6" class="form-control" id="contextStatus" v-model="newContext.status">' +
        '' +
        '<option>Unverified</option><option>Verified</option></select>\n' +
        '            </div>\n' +
        '          </form>\n' +
        '        </div>\n' +
        '        <div class="modal-footer">\n' +
        '          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>\n' +
        '          <button type="button" class="btn btn-primary" v-on:click="saveContext" data-bs-dismiss="modal">Save</button>\n' +
        '        </div>\n' +
        '      </div>\n' +
        '    </div>\n' +
        '  </div>\n' +
        '\n' +
        '</div>' +
        '' +
        '<div>{{contextsList[0]}}</div>' ,
        data()
        {
            return {
                contextsList: [],
                newContext:{
                    name:"",
                    description:""

                }
            }
        },
        methods:{
            saveContext(){
                const contextService = new ContextService();
                console.log("Saving Context");
                contextService.create(this.newContext, null);

            },
            findAllContext(){
                console.log("findAll Contexts");
                const contextService = new ContextService();
                contextService.findAll(r=>{
                    this.contextsList = r.data;
                });
            },
            showContext(id)
            {
                console.log(id);
                this.$router.push({

                    path:"/context",
                    params:{
                        context:this.contextsList[id]

                }});
            }

        },
    mounted(){
     ///   this.findAllContext();
        // this.findAllContext();

    }

}

const showContext = {
    template:`
<div>
        <h1>{{ Context  }}</h1>         
</div>
   `,
    data(){
        return {
            Context: {
                name:"AHmed"
            }
        }
    },
    methods:{

    },

    beforeMount(){
        // console.log(this.$route.params.context.name);
        // this.Context = this.$route.params.context;

    }
}

const variableComponent = {

    template: '<div class="row g-3">\n' +
        '  <div class="col-sm-12">\n' +
        '    <h3>Variables</h3>\n' +
        '  </div>\n' +
        '  <div class="col-sm-10">\n' +
        '    <input type="text" class="form-control" placeholder="search" aria-label="search">\n' +
        '  </div>\n' +
        '\n' +
        '  <div class="col-sm-1">\n' +
        '    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#variableModal">Add</button>\n' +
        '\n' +
        '  </div>\n' +
        '  <div class="col-sm-1">\n' +
        '    <button class="btn btn-primary" v-on:click="foo">Load</button>\n' +
        '\n' +
        '  </div>\n' +
        '  <div class="col-12">\n' +
        '    <table class="table table-striped table-hover">\n' +
        '      <thead>\n' +
        '      <tr>\n' +
        '        <th scope="col">\n' +
        '          name\n' +
        '        </th>\n' +
        '        <th scope="col">\n' +
        '          description\n' +
        '        </th>\n' +
        '        <th scope="col">\n' +
        '          status\n' +
        '        </th>\n' +
        '        <th scope="col">\n' +
        '          Action\n' +
        '        </th>\n' +
        '      </tr>\n' +
        '      </thead>\n' +
        '      <tbody>\n' +
        '      <tr v-for="x in contextList">\n' +
        '        <th scope="row">\n' +
        '          {{x.name}}\n' +
        '        </th>\n' +
        '        <th scope="row">\n' +
        '          {{x.description}}\n' +
        '        </th>\n' +
        '        <th scope="row">\n' +
        '          {{x.status}}\n' +
        '        </th>\n' +
        '        <th>\n' +
        '          <button class="btn-primary btn btn-secondary" v-on:click="showContexts(x.id)">\n' +
        '            Show\n' +
        '          </button>\n' +
        '        </th>\n' +
        '\n' +
        '      </tr>\n' +
        '\n' +
        '      </tbody>\n' +
        '    </table>\n' +
        '  </div>\n' +
        '\n' +
        '  <div class="modal fade" id="variableModal" tabindex="-1" aria-labelledby="variableModal" aria-hidden="true">\n' +
        '    <div class="modal-dialog">\n' +
        '      <div class="modal-content">\n' +
        '        <div class="modal-header">\n' +
        '          <h5 class="modal-title" id="exampleModalLabel">Create A Variable</h5>\n' +
        '          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>\n' +
        '        </div>\n' +
        '        <div class="modal-body">\n' +
        '          <form class="row g-3">\n' +
        '            <div class="col-md-12">\n' +
        '              <label for="contextName" class="form-label">Name</label>\n' +
        '              <input type="text" class="form-control" id="contextName" v-model="newVariable.name"/>\n' +
        '            </div>\n' +
        '            <div class="col-md-12">\n' +
        '              <label for="contextDescription" class="form-label">Description</label>\n' +
        '              <textarea type="text" rows="2" class="form-control" id="contextDescription" v-model="newVariable.description"/>\n' +
        '            </div>\n' +
        '            <div class="col-md-12">\n' +
        '              <label for="contextName" class="form-label">type</label>\n' +
        '              <select type="text" class="form-control" id="contextName" v-model="newVariable.type">' +
        '<option>String</option><option>Integer</option><option>Decimal</option>\n' +
        '</select>\n' +
        '            </div>\n' +
        '            <div class="col-md-12">\n' +
        '              <label for="contextName" class="form-label">Context</label>\n' +
        '              <input type="text" class="form-control" id="contextName" v-model="newVariable.context"/>\n' +
        '            </div>\n' +
        '            <div class="col-md-12">\n' +
        '              <label for="contextName" class="form-label">Status</label>\n' +
        '              <input type="text" class="form-control" id="contextName" v-model="newVariable.status"/>\n' +
        '            </div>\n' +
        '          </form>\n' +
        '        </div>\n' +
        '        <div class="modal-footer">\n' +
        '          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>\n' +
        '          <button type="button" class="btn btn-primary" v-on:click="saveVariable" data-bs-dismiss="modal">Save</button>\n' +
        '        </div>\n' +
        '      </div>\n' +
        '    </div>\n' +
        '  </div>\n' +
        '\n' +
        '</div>' +
        '' +
        '<div>{{newVariable}}</div>' ,
    data()
    {
        return {
            newVariable:{
                name:"",
                description:""
            },

        }
    },
    methods:{
        saveVariable(){
            var variableService= new VariableService();

            console.log("Saving Variable");
            variableService.create(this.newVariable, null);

        }
    }
}

// 2. Define some routes
// Each route should map to a component.
// We'll talk about nested routes later.
const routes = [
    { path: '/', component: contextComponent },
    { path: '/context', component: showContext, props: true },
    { path: '/variable', component: variableComponent },
]

// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
const router = VueRouter.createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: VueRouter.createWebHashHistory(),
    routes, // short for `routes: routes`
})
const app = Vue.createApp({
    data() {
        return {
            count: 20
        }
    }
})
app.use(router)
app.mount('#app')