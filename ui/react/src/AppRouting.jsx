import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ErrorComponent from './api/ErrorComponent'
import HeaderComponent from './forms/common/HeaderComponent'
import FooterComponent from './forms/common/FooterComponent'
import HomeListComponent from './forms/home/HomeListComponent'
import HomeDetailComponent from './forms/home/HomeDetailComponent'
import MyCaseListComponent from './forms/myCase/MyCaseListComponent';
import MyCaseDetailComponent from './forms/myCase/MyCaseDetailComponent';

class AppRouting extends React.Component {
 render = () => {
  return (
   <div className="AppRouting">
    <Router>
     <>
      <HeaderComponent />
      <Switch>
       <Route path="/" exact component={HomeListComponent} />
       <Route path="/home" exact component={HomeListComponent} />
       <Route path="/home-detail/:id" exact component={HomeDetailComponent} />
       <Route path="/my-case-list" exact component={MyCaseListComponent} />
       <Route path="/my-case-detail/:id" exact component={MyCaseDetailComponent} />

       <Route component={ErrorComponent} />
      </Switch>
      <FooterComponent />
     </>
    </Router>
   </div>
  );
 }
}

export default AppRouting;