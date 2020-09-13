import React from 'react';


class LogoutComponent extends React.Component {
    render = () => {
        return (
            <div className="container">
                <h1>You are logged out</h1>
                <div className="container">
                    Thank you. Come again!
                 </div>
            </div>
        );
    }
}

export default LogoutComponent;