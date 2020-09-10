import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MycasesDetailComponent } from './mycases-detail.component';

describe('MycasesDetailComponent', () => {
  let component: MycasesDetailComponent;
  let fixture: ComponentFixture<MycasesDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MycasesDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MycasesDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
