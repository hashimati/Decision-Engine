import axios from "axios/dist/axios.js";


// class variable {

//     constructor(id, name, description, status, dateCreated, dateUpdated) {
//         this.id = id;
//         this.name = name;
//         this.description = description;
//         this.status = status;
//         this.dateCreated = dateCreated;
//         this.dateUpdated = dateUpdated;

//     }
// }



let VariableService ={

    findAll(fn) {
        axios.get('http://localhost:3030/api/v1/variable/findAll'
            // ,
            // {
            //     headers: {
            //         'Authorization': token
            //     }
            // }
        ).then(response => fn(response)).catch(error => console.log(error))
    },
      findAllByContext(context,fn) {
        axios.get('http://localhost:3030/api/v1/variable/findAllByContext?query='+context
            // ,
            // {
            //     headers: {
            //         'Authorization': token
            //     }
            // }
        ).then(response => fn(response)).catch(error => console.log(error))
    },
    
    findById(id, fn) {
        axios
            .get('http://localhost:3030/api/v1/variable/get?id=' + id
                // ,
                // {
                //     headers: {
                //         'Authorization': token
                //     }
                // }
            )
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    create(variable, fn) {

        axios
            .post('http://localhost:3030/api/v1/variable/save', variable)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    update(id, variable, fn) {
        axios
            .put('http://localhost:3030/api/v1/variable/update', variable
                // ,
                // {
                //     headers: {
                //         'Authorization': token
                //     }
                // }
            )
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    delete(id, fn) {
        axios
            .delete('http://localhost:3030/api/v1/variable/delete/' + id
                // ,
                // {
                //     headers: {
                //         'Authorization': token
                //     }
                // }
            )
            .then(response => fn(response))
            .catch(error => console.log(error))
    }
}

export default VariableService;
