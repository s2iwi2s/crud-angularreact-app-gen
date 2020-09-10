import React from 'react';

import { withRouter } from 'react-router';

class HeaderComponent extends React.Component {
    render = () => {
        return (
            <header className="bg-dark">
                <nav className="container navbar navbar-expand-md navbar-dark">
                    <div><a className="navbar-brand" href="/">My React Crude App</a></div>
                </nav>
            </header>
        );
    }
}

export default withRouter(HeaderComponent);