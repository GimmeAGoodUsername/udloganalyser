import { Component } from "react";
import AuthContext from '../context/AuthContext'
import { srApi } from "../api/SrApi";
import { Container } from 'semantic-ui-react'
import UserPanel from './UserPanel'
import { handleLogError } from "../misc/Helpers";
class UserPage extends Component {
    static contextType = AuthContext
    state = {
        srUser :null   
     }
    componentDidMount() {
        const Auth = this.context
        const user = Auth.getUser() 

        this.handleUser()
    }

    handleUser = () => {
        const Auth = this.context
        const user = Auth.getUser()
        srApi.getUser(user.name, user).then(response => {
            this.setState({srUser: response.data})
        }
        ).catch(error =>
            handleLogError(error.response.data));
    }

    

    render() {
        const {srUser} = this.state.user
        return (<Container><UserPanel /></Container>)
    }

}

export default UserPage