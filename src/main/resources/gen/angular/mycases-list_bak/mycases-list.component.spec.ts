import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MycasesListComponent } from './mycases-list.component';

describe('MycasesListComponent', () => {
  let component: MycasesListComponent;
  let fixture: ComponentFixture<MycasesListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MycasesListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MycasesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
