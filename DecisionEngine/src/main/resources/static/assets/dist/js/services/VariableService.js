class Variable{
    constructor(context, name, type, description,  dateCreated, dateUpdated) {
        this.context = context;
        this.name = name;
        this.type = type;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;

    }
}
class VariableService{
    findAll( fn) {
        axios
            .get('/api/v1/variable/findAll'
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

    findById(id,  fn) {
        axios
            .get('/api/v1/variable/find/' + id
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

    create(variable,  fn) {

        axios
            .post('/api/v1/variable/save', variable)
            .then(response => fn(response))
            .catch(error => console.log(error))
    }

    update(id, variable,  fn) {
        axios
            .put('/api/v1/variable/update', variable
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
            .delete('/api/v1/variable/delete/' + id
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





