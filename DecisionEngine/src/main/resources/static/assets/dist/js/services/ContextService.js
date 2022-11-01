class Context{

    constructor(id, name, description, status,dateCreated,dateUpdated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;

    }
}

class ContextService {
    findAll(fn) {
        axios.get('/api/v1/context/findAll'
            // ,
            // {
            //     headers: {
            //         'Authorization': token
            //     }
            // }
        ).then(response => fn(response)).catch(error => console.log(error))
    }

    findById(id, fn) {
        axios
            .get('/api/v1/context/get?id=' + id
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

    create(context, fn) {

        axios
            .post('/api/v1/context/save', context)
            .then(response => fn(response))
            .catch(error => console.log(error))
    }

    update(id, context, fn) {
        axios
            .put('/api/v1/context/update', context
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

    delete(id, fn) {
        axios
            .delete('/api/v1/context/delete/' + id
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