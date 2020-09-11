import React from 'react';

import { withRouter } from 'react-router';
import { Link } from 'react-router-dom';

class HeaderComponent extends React.Component {
    render = () => {
        return (
            <header className="bg-dark">
                <nav className="container navbar navbar-expand-md navbar-dark bg-dark">
                    <div><a className="navbar-brand" href="/">My React Crude App</a></div>

                    <ul className="navbar-nav">
                        <li key="my-case-list"><Link className="nav-link" to="/my-case-list">My Cases</Link></li>
                        <li key="part-item-list"><Link className="nav-link" to="/part-item-list">Part Items</Link></li>
                        <li key="serial-item-list"><Link className="nav-link" to="/serial-item-list">Serial Items</Link></li>
                        <li key="customer-list"><Link className="nav-link" to="/customer-list">Customers</Link></li>
                        <li key="product-list"><Link className="nav-link" to="/product-list">Products</Link></li>
                        <li key="end-user-list"><Link className="nav-link" to="/end-user-list">Users</Link></li>
                        <li key="address-list"><Link className="nav-link" to="/address-list">Address</Link></li>
                    </ul>
                </nav>
            </header>
        );
    }
}

export default withRouter(HeaderComponent);
