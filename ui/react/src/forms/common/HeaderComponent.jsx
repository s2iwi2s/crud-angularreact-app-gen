import React from 'react';

import { withRouter } from 'react-router';
import { Link } from 'react-router-dom';
import AuthenticationService from '../../security/AuthenticationService'

class HeaderComponent extends React.Component {
    render = () => {
        const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
        return (
            <header className="bg-dark">
                <nav className="container navbar navbar-expand-md navbar-dark bg-dark">
                    <div><a className="navbar-brand" href="/home ">My React Crude App</a></div>
                    {
                        isUserLoggedIn &&
                        <ul className="navbar-nav">
                            <li key="my-case-list"><Link className="nav-link" to="/my-case-list">My Cases</Link></li>
                            <li key="part-item-list"><Link className="nav-link" to="/part-item-list">Part Items</Link></li>
                            <li key="serial-item-list"><Link className="nav-link" to="/serial-item-list">Serial Items</Link></li>
                            <li key="customer-list"><Link className="nav-link" to="/customer-list">Customers</Link></li>
                            <li key="product-list"><Link className="nav-link" to="/product-list">Products</Link></li>
                            <li key="end-user-list"><Link className="nav-link" to="/end-user-list">Users</Link></li>
                            <li key="address-list"><Link className="nav-link" to="/address-list">Address</Link></li>
                        </ul>
                    }
                    <ul className="navbar-nav navbar-collapse justify-content-end">
                        {!isUserLoggedIn && <li key="loginId"><Link className="nav-link" to="/login">Login</Link></li>}
                        {isUserLoggedIn && <li key="logoutId"><Link className="nav-link" to="/logout" onClick={AuthenticationService.logout} >Logout</Link></li>}
                    </ul>
                </nav>
            </header>
        );
    }
}

export default withRouter(HeaderComponent);
