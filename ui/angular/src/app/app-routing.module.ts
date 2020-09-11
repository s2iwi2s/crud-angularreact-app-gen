import { NgModule } from '@angular/core'
import { Routes, RouterModule } from '@angular/router'

import { HomeListComponent } from './home-list/home-list.component'
import { HomeDetailComponent } from './home-detail/home-detail.component'

import { MyCaseListComponent } from './my-case-list/my-case-list.component'
import { MyCaseDetailComponent } from './my-case-detail/my-case-detail.component'

import { CodeGroupsListComponent } from './code-groups-list/code-groups-list.component'
import { CodeGroupsDetailComponent } from './code-groups-detail/code-groups-detail.component'

import { PartItemListComponent } from './part-item-list/part-item-list.component'
import { PartItemDetailComponent } from './part-item-detail/part-item-detail.component'

import { SerialItemListComponent } from './serial-item-list/serial-item-list.component'
import { SerialItemDetailComponent } from './serial-item-detail/serial-item-detail.component'

import { ProductListComponent } from './product-list/product-list.component'
import { ProductDetailComponent } from './product-detail/product-detail.component'

const routes: Routes = [
  { path: '', redirectTo: '/home-list', pathMatch: 'full' },
  {
    path: 'home-list',
    component: HomeListComponent,
  },
  {
    path: 'home-add',
    component: HomeDetailComponent,
  },
  {
    path: 'home-detail/:id',
    component: HomeDetailComponent,
  },
  {
    path: 'home-remove/:id',
    component: MyCaseListComponent,
  },
  {
    path: 'my-case-list',
    component: MyCaseListComponent,
  },
  {
    path: 'my-case-add',
    component: MyCaseDetailComponent,
  },
  {
    path: 'my-case-detail/:id',
    component: MyCaseDetailComponent,
  },
  {
    path: 'my-case-remove/:id',
    component: MyCaseListComponent,
  },

  {
    path: 'code-groups-list',
    component: CodeGroupsListComponent,
  },
  {
    path: 'code-groups-add',
    component: CodeGroupsDetailComponent,
  },
  {
    path: 'code-groups-detail/:id',
    component: CodeGroupsDetailComponent,
  },
  {
    path: 'code-groups-remove/:id',
    component: CodeGroupsListComponent,
  },

  {
    path: 'part-item-list',
    component: PartItemListComponent,
  },
  {
    path: 'part-item-add',
    component: PartItemDetailComponent,
  },
  {
    path: 'part-item-detail/:id',
    component: PartItemDetailComponent,
  },
  {
    path: 'part-item-remove/:id',
    component: PartItemListComponent,
  },

  {
    path: 'serial-item-list',
    component: SerialItemListComponent,
  },
  {
    path: 'serial-item-add',
    component: SerialItemDetailComponent,
  },
  {
    path: 'serial-item-detail/:id',
    component: SerialItemDetailComponent,
  },
  {
    path: 'serial-item-remove/:id',
    component: SerialItemListComponent,
  },

  {
    path: 'product-list',
    component: ProductListComponent,
  },
  {
    path: 'product-add',
    component: ProductDetailComponent,
  },
  {
    path: 'product-detail/:id',
    component: ProductDetailComponent,
  },
  {
    path: 'product-remove/:id',
    component: ProductListComponent,
  },
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
