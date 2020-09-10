import { TestBed } from '@angular/core/testing';

import { MycasesService } from './mycases.service';

describe('MycasesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MycasesService = TestBed.get(MycasesService);
    expect(service).toBeTruthy();
  });
});
