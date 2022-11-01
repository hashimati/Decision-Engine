import axios from "axios/dist/axios.js";


// class Context {

//     constructor(id, name, description, status, dateCreated, dateUpdated) {
//         this.id = id;
//         this.name = name;
//         this.description = description;
//         this.status = status;
//         this.dateCreated = dateCreated;
//         this.dateUpdated = dateUpdated;

//     }
// }



let ContextService ={

    findAll(fn) {
        axios.get('http://localhost:3030/api/v1/context/findAll'
            // ,
            // {
            //     headers: {
            //         'Authorization': token
            //     }
            // }
        ).then(response => fn(response)).catch(error => console.log(error))
    },
    findAllNames(fn) {
        axios.get('http://localhost:3030/api/v1/context/findAllNames'
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
            .get('http://localhost:3030/api/v1/context/get?id=' + id
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

    create(context, fn) {

        axios
            .post('http://localhost:3030/api/v1/context/save', context)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    update(id, context, fn) {
        axios
            .put('http://localhost:3030/api/v1/context/update', context
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
            .delete('http://localhost:3030/api/v1/context/delete/' + id
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

export default ContextService;
