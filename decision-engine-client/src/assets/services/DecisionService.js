import axios from "axios/dist/axios.js";


// class decision {

//     constructor(id, name, description, status, dateCreated, dateUpdated) {
//         this.id = id;
//         this.name = name;
//         this.description = description;
//         this.status = status;
//         this.dateCreated = dateCreated;
//         this.dateUpdated = dateUpdated;

//     }
// }



let DecisionService ={

    findAll(fn) {
        axios.get('http://localhost:3030/api/v1/decision/findAll'
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
            .get('http://localhost:3030/api/v1/decision/get?id=' + id
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

    create(decision, fn) {

        axios
            .post('http://localhost:3030/api/v1/decision/save', decision)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    update(id, decision, fn) {
        axios
            .put('http://localhost:3030/api/v1/decision/update', decision
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
            .delete('http://localhost:3030/api/v1/decision/delete/' + id
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

export default DecisionService;
