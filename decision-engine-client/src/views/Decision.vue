<template>
    
    <div>
        <form class="row g-3">
              <div class="col-md-12">
                <label for="decisionName" class="form-label">Name</label>
                <input type="text" class="form-control" id="decisionName" v-model="c.name"/>
              </div>
              <div class="col-md-12">
                <label for="decisionDescription" class="form-label">Description</label>
                <textarea type="text" rows="6" class="form-control" id="decisionDescription" v-model="c.description"/>
              </div>
              <div class="col-md-12">
                <label for="decisionContext" class="form-label">Context</label>
                <select class="form-control" id="decisionContext" v-model="c.context" >
                    <option v-for="x in contextNames">{{ x }}</option>
                </select>
              </div>
              <div class="col-md-12">
                <label for="decisionDescription" class="form-label">Status</label>
                <select type="text" rows="6" class="form-control" id="decisionStatus" v-model="c.status">
                  <option>Unverified</option><option>Verified</option>
                </select>
              </div>
              
            </form>
            <div class = "row g-3">
                <button type="button" class="btn btn-danger" v-on:click="deleteDecision" >Delete</button>
                <button type="button" class="btn btn-warning" v-on:click="updateDecision">Update</button>
                <button type="button" class="btn btn-info"   data-bs-toggle="modal"
            data-bs-target="#ruleModal">Rules</button>

            </div>
    </div>

    <div class="modal fade" id="ruleModal" tabindex="-1" aria-labelledby="ruleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
  
              <h5 class="modal-title" id="exampleModalLabel">Rules</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <form class="row g-3">
                <div class="col-md-3">
                  <label for="variable" class="form-label">variable</label>
                  <select class="form-control" v-on:change="setCurrentOperandsList" id="variableName" v-model="rule.variable">
                    <option v-for="y in variableList">{{ y }}</option>
                  </select>
                </div>
                <div class="col-md-3">
                  <label for="variableDescription" class="form-label">Operand</label>
                  <select class="form-control" id="operand" v-model="rule.operator">
                    <option v-for="y in currentOperands">{{ y }}</option>
                  </select>

                </div>
              <div class="col-md-3">
                <label for="variableContext" class="form-label">value</label>
                <input type="text" class="form-control" id="variableName" v-model="rule.value"/>

              </div>
                <div class="col-md-3">
                    <br/>
                    <div class="row">
                        <div class="col-sm-6">
                        <button type="button" class="btn btn-success" v-on:click="and">AND</button>
                    </div>
                    <div class="col-sm-6">
                        <button type="button" class="btn btn-warning" v-on:click="or">OR</button>
                    </div>
                </div>
                </div>
                <div class="col-md-12">
                    <label for="variableContext" class="form-label">expression</label>
                    <textarea class="form-control" id="expression" v-model="c.rule"/>
                </div>
              </form>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <!-- <button type="button" class="btn btn-primary" v-on:click="saveExpression" data-bs-dismiss="modal">Save</button> -->
            </div>
          </div>
        </div>


       
      </div>
</template>
<script scoped>

    import VariableService from '@/assets/services/VariableService';
    import ContextService from '@/assets/services/ContextService'
    import DecisionService from '@/assets/services/DecisionService';
    export default{

         data() {
                return {
                    c: {}, 
                    contextNames:[], 
                    rule:{
                      value:"", variable:"", operator:"", expression:""

                    },
                    expressionValue:"",
                    variables:[],
                    variableList :[],
                    variableDic:{},
                    operands : [ "==", "!=", ">", "<",">=","<="],
                    stringOperands:["==", "!=" , "matches"], 
                    currentOperands : []
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
                console.log("Loading decision " + id); 
                DecisionService.findById(id, r=>{
                this.c = r.data;

                  VariableService.findAllByContext(r.data.context, x=>{
                    this.variables = x.data;
                    x.data.forEach(u=>this.variableDic[u.name] =u.type);
                    this.variableList = Object.keys(this.variableDic);
                  });

            });
                console.log("name  " + this.c.name);
            },

            updateDecision(){
                DecisionService.update(this.c.id, this.c, r=>{
                    console.log(r.data); 
                })
            }, 
            deleteDecision(){
                DecisionService.delete(this.c.id, r=>{
                    console.log(r.data); 
                })
            },
            loadVariablesByContext() {
              console.log("I'm here " + this.c.context);


              VariableService.findAllByContext(this.c.context, r => {
                this.variables = r.data;


              });
              this.variables.forEach(x => this.variableDic[x.name] = x.type);
              this.variableList = Object.keys(this.variableDic);

              console.log(this.variableDic);
              console.log(this.variableList);
              
            },
          and(){
            this.rule.expression = this.c.rule ;
            if(!this.rule.operator || !this.rule.variable || !this.rule.value) {
              return;
            }

            if(this.rule.expression) {
              this.rule.expression += " and ( " + this.rule.variable + " " + this.rule.operator + " " + this.rule.value + " )";
            }
            if(!this.rule.expression)
            {
              this.rule.expression += " ( " + this.rule.variable + " " + this.rule.operator + " " + this.rule.value + " )";

            }

            this.rule.variable ="";
            this.rule.operator="";
            this.rule.value = "";
            this.c.rule = this.rule.expression
            },
          or(){
            this.rule.expression = this.c.rule ;
            if(!this.rule.operator || !this.rule.variable || !this.rule.value) {
              return;
            }

            if(this.rule.expression) {
              this.rule.expression += " or ( " + this.rule.variable + " " + this.rule.operator + " " + this.rule.value + " )";
            }
            if(!this.rule.expression)
            {
              this.rule.expression += " ( " + this.rule.variable + " " + this.rule.operator + " " + this.rule.value + " )";
              
            }

            this.rule.variable ="";
            this.rule.operator="";
            this.rule.value = "";
            this.c.rule = this.rule.expression
          }, 
          saveExpression(){
            this.c.rule = this.rule.expression; 

          }, 
          setCurrentOperandsList(){
            console.log("i'm scrolling!"); 
            for(var i = 0; i < this.variables.length; i++){
              console.log(this.variables[i].type); 
              console.log(this.variables[i].name); 
              console.log(this.rule.variable); 
                if(this.variables[i].name === this.rule.variable)
                {
                  
                  
                  if(this.variables[i].type === "string")
                  {
                      this.currentOperands = this.stringOperands; 
                  }
                  else if(this.variables[i].type === "integer"){
                     this.currentOperands= this.operands;
                  }
                  break; 
                }

            }
          }
        },


        beforeMount(){
            console.log("loading decision");
            this.findById(this.$route.params.id); 
            this.findAllContextNames();

        },
        mounted() {
           console.log("Created.....");
          this.currentOperands = this.operands; 
        }
    }
</script>