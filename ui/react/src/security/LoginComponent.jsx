import React from 'react';

import AuthenticationService from './AuthenticationService.js'

class LoginComponent extends React.Component {
    constructor() {
        super();
        this.state = {
            username: 'test',
            password: 'test',
            hasLoginFailed: false,
            message: '',
            alertClass: ''
        }
    }

    changeHandler = event => {
        console.log(`name: ${event.target.name}, value: ${event.target.value}`);
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    loginClicked = event => {
        console.log('state: ', this.state);
        // if(this.state.username==='test' && this.state.password==='test') {
        //     AuthenticationService.registerSucessfulLogin(this.state.username, this.state.password);
        //     console.log('success');
        //     this.setState({
        //         hasLoginFailed : false,
        //         showSuccessMsg: true
        //     });
        //     this.props.history.push(`/welcome/${this.state.username}`);
        // }else{
        //     this.setState({
        //         hasLoginFailed : true,
        //         showSuccessMsg: false
        //     });
        // }
        //event.preventDefault();
        AuthenticationService.executeJwtAuthenticationService(this.state.username, this.state.password)
            //executeBasicAuthenticationService(this.state.username, this.state.password)
            .then(response => {
                console.log('[LoginComponent.loginClicked]: response', response);
                AuthenticationService.registerJwtSucessfulLogin(this.state.username, response.data.token)
                //registerBasicAuthSucessfulLogin(this.state.username, this.state.password);
                this.setState({
                    hasLoginFailed: false,
                    message: 'Login Successful!',
                    alertClass: 'alert-success'
                });
                this.props.history.push(`/home`);
            }
            ).catch(error => {
                console.log(`[LoginComponent.loginClicked]: error=${JSON.stringify(error)}`);
                this.setState({
                    alertClass: 'alert-danger',
                    message: `Invalid ID or password. Please try again.`,
                    hasLoginFailed: true
                });
            });
    }

    render = () => {
        return (
            <div>
                <form className="form-signin">
                    <h1 className="h3 mb-3 font-weight-normal">Please sign in</h1>

                    {this.state.message != null && <div className={'text-center alert ' + this.state.alertClass}>{this.state.message}</div>}

                    <label htmlFor="inputUsername" className="sr-only">Username</label>
                    <input type="text" name="username" id="inputUsername"
                        value={this.state.username}
                        className="form-control" placeholder="Enter Username" required autoFocus
                        onChange={(e) => this.changeHandler(e)} />

                    <label htmlFor="inputPassword" className="sr-only">Password</label>
                    <input type="password" name="password" id="inputPassword"
                        value={this.state.password}
                        className="form-control" placeholder="Enter Password" required onChange={(e) => this.changeHandler(e)} />

                    <button type="button" className="btn btn-lg btn-primary btn-block" onClick={this.loginClicked}>Sign in</button>
                </form>
            </div>
        );
    }
}

export default LoginComponent;