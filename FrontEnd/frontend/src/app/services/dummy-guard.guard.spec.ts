import { TestBed } from '@angular/core/testing';

import { DummyGuardGuard } from './dummy-guard.guard';

describe('DummyGuardGuard', () => {
  let guard: DummyGuardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(DummyGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
