import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProduccionCreate } from './produccion-create';

describe('ProduccionCreate', () => {
  let component: ProduccionCreate;
  let fixture: ComponentFixture<ProduccionCreate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProduccionCreate],
    }).compileComponents();

    fixture = TestBed.createComponent(ProduccionCreate);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
