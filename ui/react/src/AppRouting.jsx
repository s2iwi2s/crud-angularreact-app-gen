import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ErrorComponent from './api/ErrorComponent'
import HeaderComponent from './forms/common/HeaderComponent'
import FooterComponent from './forms/common/FooterComponent'
import HomeListComponent from './forms/home/HomeListComponent'
import HomeDetailComponent from './forms/home/HomeDetailComponent'
import MyCaseListComponent from './forms/myCase/MyCaseListComponent';
import MyCaseDetailComponent from './forms/myCase/MyCaseDetailComponent';
import PartItemListComponent from './forms/partItem/PartItemListComponent';
import PartItemDetailComponent from './forms/partItem/PartItemDetailComponent';
import SerialItemListComponent from './forms/serialItem/SerialItemListComponent';
import SerialItemDetailComponent from './forms/serialItem/SerialItemDetailComponent';
import CustomerListComponent from './forms/customer/CustomerListComponent';
import CustomerDetailComponent from './forms/customer/CustomerDetailComponent';
import ProductListComponent from './forms/product/ProductListComponent';
import ProductDetailComponent from './forms/product/ProductDetailComponent';
import EndUserListComponent from './forms/endUser/EndUserListComponent';
import EndUserDetailComponent from './forms/endUser/EndUserDetailComponent';
import AddressListComponent from './forms/address/AddressListComponent';
import AddressDetailComponent from './forms/address/AddressDetailComponent';
import LoginComponent from './security/LoginComponent'
import LogoutComponent from './security/LogoutComponent'
import AuthenticatedRoute from './security/AuthenticatedRoute'

class AppRouting extends React.Component {
 render = () => {
  return (
   <div className="AppRouting">
    <Router>
     <>
      <HeaderComponent />
      <div className="container">
       <Switch>
        <Route path="/" exact component={LoginComponent} />
        <Route path="/login" component={LoginComponent} />
        <AuthenticatedRoute path="/logout" component={LogoutComponent} />
        <AuthenticatedRoute path="/home" exact component={HomeListComponent} />
        <AuthenticatedRoute path="/home-detail/:id" exact component={HomeDetailComponent} />
        <AuthenticatedRoute path="/my-case-list" exact component={MyCaseListComponent} />
        <AuthenticatedRoute path="/my-case-detail/:id" exact component={MyCaseDetailComponent} />
        <AuthenticatedRoute path="/part-item-list" exact component={PartItemListComponent} />
        <AuthenticatedRoute path="/part-item-detail/:id" exact component={PartItemDetailComponent} />
        <AuthenticatedRoute path="/serial-item-list" exact component={SerialItemListComponent} />
        <AuthenticatedRoute path="/serial-item-detail/:id" exact component={SerialItemDetailComponent} />
        <AuthenticatedRoute path="/customer-list" exact component={CustomerListComponent} />
        <AuthenticatedRoute path="/customer-detail/:id" exact component={CustomerDetailComponent} />
        <AuthenticatedRoute path="/product-list" exact component={ProductListComponent} />
        <AuthenticatedRoute path="/product-detail/:id" exact component={ProductDetailComponent} />
        <AuthenticatedRoute path="/end-user-list" exact component={EndUserListComponent} />
        <AuthenticatedRoute path="/end-user-detail/:id" exact component={EndUserDetailComponent} />
        <AuthenticatedRoute path="/address-list" exact component={AddressListComponent} />
        <AuthenticatedRoute path="/address-detail/:id/:endUserId" exact component={AddressDetailComponent} />
        <AuthenticatedRoute path="/address-detail/:id" component={AddressDetailComponent} />

        <Route component={ErrorComponent} />
       </Switch>
      </div>
      <FooterComponent />
     </>
    </Router>
   </div>
  );
 }
}

export default AppRouting;