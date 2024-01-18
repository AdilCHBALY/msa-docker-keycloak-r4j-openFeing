import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TauxDataTableComponent } from './taux-data-table.component';

describe('TauxDataTableComponent', () => {
  let component: TauxDataTableComponent;
  let fixture: ComponentFixture<TauxDataTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TauxDataTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TauxDataTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
