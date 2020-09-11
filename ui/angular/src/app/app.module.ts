import { BrowserModule } from '@angular/platform-browser'
import { NgModule } from '@angular/core'
import { HttpClientModule } from '@angular/common/http'
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import {
  MatButtonModule,
  MatCardModule,
  MatInputModule,
  MatListModule,
  MatToolbarModule,
  MatSelectModule,
  MatFormFieldModule,
  MatTableModule,
  MatPaginatorModule,
  MatAutocompleteModule,
} from '@angular/material'
import { MatIconModule } from '@angular/material/icon'
import { MatGridListModule } from '@angular/material/grid-list'

import { MatDialogModule, MatDialog } from '@angular/material/dialog'

import { BrowserAnimationsModule } from '@angular/platform-browser/animations'

import { AppRoutingModule } from './app-routing.module'
import { AppComponent } from './app.component'

import { HomeListComponent } from './home-list/home-list.component'
import { HomeDetailComponent } from './home-detail/home-detail.component'

import { MyCaseListComponent } from './my-case-list/my-case-list.component'
import { MyCaseDetailComponent } from './my-case-detail/my-case-detail.component'

import { CodeGroupsListComponent } from './code-groups-list/code-groups-list.component'
import { CodeGroupsDetailComponent } from './code-groups-detail/code-groups-detail.component'

import { PartItemListComponent } from './part-item-list/part-item-list.component'
import { PartItemDetailComponent } from './part-item-detail/part-item-detail.component'

import { SerialItemListComponent } from './serial-item-list/serial-item-list.component'
import { SerialItemDetailComponent, PartItemDialog } from './serial-item-detail/serial-item-detail.component'

import { ProductListComponent } from './product-list/product-list.component'
import { ProductDetailComponent } from './product-detail/product-detail.component'

@NgModule({
  declarations: [
    AppComponent,
    HomeListComponent,
    HomeDetailComponent,
    CodeGroupsListComponent,
    CodeGroupsDetailComponent,
    PartItemListComponent,
    PartItemDetailComponent,
    SerialItemListComponent,
    SerialItemDetailComponent, PartItemDialog,
    MyCaseListComponent,
    MyCaseDetailComponent,
    ProductListComponent,
    ProductDetailComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatSelectModule,
    MatFormFieldModule,
    MatTableModule,
    MatPaginatorModule,
    MatAutocompleteModule,
    MatGridListModule,
    MatIconModule,
    MatDialogModule,
  ],
  entryComponents: [PartItemDialog],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
