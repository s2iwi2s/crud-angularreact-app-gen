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
       <Route path="/part-item-list" exact component={PartItemListComponent} />
       <Route path="/part-item-detail/:id" exact component={PartItemDetailComponent} />
       <Route path="/serial-item-list" exact component={SerialItemListComponent} />
       <Route path="/serial-item-detail/:id" exact component={SerialItemDetailComponent} />
       <Route path="/customer-list" exact component={CustomerListComponent} />
       <Route path="/customer-detail/:id" exact component={CustomerDetailComponent} />
       <Route path="/product-list" exact component={ProductListComponent} />
       <Route path="/product-detail/:id" exact component={ProductDetailComponent} />
       <Route path="/end-user-list" exact component={EndUserListComponent} />
       <Route path="/end-user-detail/:id" exact component={EndUserDetailComponent} />
       <Route path="/address-list" exact component={AddressListComponent} />
       <Route path="/address-detail/:id/:endUserId" exact component={AddressDetailComponent} />
       <Route path="/address-detail/:id" component={AddressDetailComponent} />

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